package com.jwt.stateless.javaloginjwt.config;

import com.jwt.stateless.javaloginjwt.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class UsersConfig {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    public MyUserDetailsService myUserDetailsService() {
        return new MyUserDetailsService(passwordEncoder);
    }
}