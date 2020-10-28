package com.epri.fx.server.service.security.auth.impl;

import com.epri.fx.server.entity.UserInfo;
import com.epri.fx.server.exception.auth.UserInvalidException;
import com.epri.fx.server.jwt.JWTInfo;
import com.epri.fx.server.service.security.auth.AuthService;
import com.epri.fx.server.service.UserService;
import com.epri.fx.server.util.user.JwtAuthenticationRequest;
import com.epri.fx.server.util.user.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = userService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            return jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
