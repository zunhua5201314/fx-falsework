package com.epri.fx.server.util.user;

import com.epri.fx.server.config.KeyConfiguration;
import com.epri.fx.server.jwt.IJWTInfo;
import com.epri.fx.server.jwt.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @Description:
 *
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:56 上午
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;



    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(),expire);
    }

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }


}
