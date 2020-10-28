package com.epri.fx.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:55 上午
 */
@Configuration
public class UserAuthConfig {

    @Value("${auth.user.token-header}")
    private String tokenHeader;

    private byte[] pubKeyByte;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader(this.getTokenHeader());
    }

    public byte[] getPubKeyByte() {
        return pubKeyByte;
    }

    public void setPubKeyByte(byte[] pubKeyByte) {
        this.pubKeyByte = pubKeyByte;
    }
}
