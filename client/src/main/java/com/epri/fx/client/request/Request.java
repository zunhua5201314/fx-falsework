package com.epri.fx.client.request;

import com.epri.fx.client.request.feign.AppRequestErrorDecoder;
import com.epri.fx.client.request.feign.AppRequestInterceptor;
import com.epri.fx.client.request.feign.FeignAPI;
import com.netflix.client.ClientFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.LoadBalancingTarget;
import feign.ribbon.RibbonClient;
import feign.slf4j.Slf4jLogger;
import okhttp3.ConnectionPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Request {

    private static final Map<String, FeignAPI> CONNECTORS = new ConcurrentHashMap<>();
    private static RibbonClient ribbonClient;

    private static String API_URL = "http://sample-client";
    private final static int CONNECT_TIME_OUT_MILLIS = 1000;
    private final static int READ_TIME_OUT_MILLIS = 60000;
    private static JacksonDecoder jacksonDecoder;
    private static JacksonEncoder jacksonEncoder;
    private static AppRequestInterceptor requestInterceptor;
    private static AppRequestErrorDecoder requestErrorDecoder;
    private static Slf4jLogger slf4jLogger;

    /**
     * @Description:
     * @param: [connectorClass, readTimeOut->设置超时时间]
     * @return: T
     * @auther: liwen
     * @date: 2019-06-05 14:33
     */
    public static <T extends FeignAPI> T connector(Class<T> connectorClass, int readTimeOut) {
        final String commandConfigKey = connectorClass.getSimpleName() + readTimeOut;

        return (T) CONNECTORS.computeIfAbsent(commandConfigKey, k -> {
            return Feign.builder()
                    .client(ribbonClient())
                    .decoder(getJacksonDecoder())
                    .encoder(getJacksonEncoder())
                    .errorDecoder(getRequestErrorDecoder())
                    .requestInterceptor(getRequestInterceptor())
                    .logger(getSlf4jLogger())
                    .logLevel(Logger.Level.FULL)
                    .retryer(Retryer.NEVER_RETRY)
                    .options(new feign.Request.Options(CONNECT_TIME_OUT_MILLIS, readTimeOut))
                    .target(connectorClass, API_URL);

        });

    }


    public static <T extends FeignAPI> T connector(Class<T> connectorClass) {
        return connector(connectorClass, READ_TIME_OUT_MILLIS);

    }


    private static JacksonDecoder getJacksonDecoder() {
        if (jacksonDecoder == null) {
            jacksonDecoder = new JacksonDecoder();
        }
        return jacksonDecoder;
    }


    private static JacksonEncoder getJacksonEncoder() {
        if (jacksonEncoder == null) {
            jacksonEncoder = new JacksonEncoder();
        }
        return jacksonEncoder;
    }

    private static AppRequestInterceptor getRequestInterceptor() {
        if (requestInterceptor == null) {
            requestInterceptor = new AppRequestInterceptor();
        }
        return requestInterceptor;
    }

    private static AppRequestErrorDecoder getRequestErrorDecoder() {
        if (requestErrorDecoder == null) {
            requestErrorDecoder = new AppRequestErrorDecoder();
        }
        return requestErrorDecoder;
    }

    private static Slf4jLogger getSlf4jLogger() {
        if (slf4jLogger == null) {
            slf4jLogger = new Slf4jLogger();
        }
        return slf4jLogger;
    }

    private static RibbonClient ribbonClient() {

        if (ribbonClient == null) {
            ribbonClient = RibbonClient.builder().delegate(new OkHttpClient(createOkHttpClient())).lbClientFactory(new LBClientFactory() {

                @Override
                public LBClient create(String clientName) {
                    IClientConfig config = ClientFactory.getNamedConfig(clientName);
                    ILoadBalancer lb = ClientFactory.getNamedLoadBalancer(clientName);
                    ZoneAwareLoadBalancer zb = (ZoneAwareLoadBalancer) lb;
                    zb.setRule(new BestAvailableRule());
                    LBClient lbClient = LBClient.create(lb, config);
                    return lbClient;
                }


            }).build();
        }
        return ribbonClient;
    }

    private static okhttp3.OkHttpClient createOkHttpClient() {
        return new okhttp3.OkHttpClient.Builder().connectionPool(new ConnectionPool())
                .build();
    }
}
