package com.epri.fx.server.runner;

import com.epri.fx.server.config.KeyConfiguration;
import com.epri.fx.server.config.UserAuthConfig;
import com.epri.fx.server.entity.RsaKey;
import com.epri.fx.server.jwt.RsaKeyHelper;
import com.epri.fx.server.service.RsaKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

/**
 * @author ace
 * @create 2017/12/17.
 */
@Configuration
public class AuthServerRunner implements CommandLineRunner {
    @Autowired
    private RsaKeyService rsaKeyService;
    private static final String REDIS_USER_PRI_KEY = "IPSM:AUTH:JWT:PRI";
    private static final String REDIS_USER_PUB_KEY = "IPSM:AUTH:JWT:PUB";

    @Autowired
    private KeyConfiguration keyConfiguration;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Override
    public void run(String... args) throws Exception {
        if (rsaKeyService.hasKey(REDIS_USER_PRI_KEY) && rsaKeyService.hasKey(REDIS_USER_PUB_KEY)) {
            keyConfiguration.setUserPriKey(RsaKeyHelper.toBytes(rsaKeyService.get(REDIS_USER_PRI_KEY)));
            keyConfiguration.setUserPubKey(RsaKeyHelper.toBytes(rsaKeyService.get(REDIS_USER_PUB_KEY)));

        } else {
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getUserSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            rsaKeyService.set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            rsaKeyService.set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));

        }
        refreshUserPubKey();
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey() {
        this.userAuthConfig.setPubKeyByte(keyConfiguration.getUserPubKey());
    }
}
