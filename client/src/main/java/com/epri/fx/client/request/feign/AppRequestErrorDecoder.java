package com.epri.fx.client.request.feign;

import com.epri.fx.client.store.ApplicatonStore;
import com.epri.fx.client.utils.AlertUtil;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @description:
 * @className: RequestErrorDecoder
 * @author: liwen
 * @date: 2020/8/13 09:36
 */
public class AppRequestErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        AlertUtil.show(response);
        return null;
    }
}
