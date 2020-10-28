package com.epri.fx.server.service.security.auth;


import com.epri.fx.server.util.user.JwtAuthenticationRequest;

public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
}
