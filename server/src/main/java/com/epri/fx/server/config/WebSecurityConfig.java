package com.epri.fx.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity//开启Spring安全
//@EnableGlobalMethodSecurity(prePostEnabled=true)//开启Spring方法级安全
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        /*
         * 配置为从内存中进行加载认证信息.
         * 这里配置了两个用户 admin和user
         */
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("admin");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("normal");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}