package com.epri.fx.client.request.feign;

import com.epri.fx.client.store.ApplicatonStore;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AppRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {

        template.header("Authorization", ApplicatonStore.getToken());
    }
}