package com.tensquare.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import util.IdWorker;
import util.JwtUtil;

/**
 * 存放所有用户微服务的bean
 */
@Component
public class UserApplicationConfig {

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 8);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

}
