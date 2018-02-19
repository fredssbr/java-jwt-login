package com.jwt.stateless.javaloginjwt.config;

import com.jwt.stateless.javaloginjwt.filter.TokenBasedAuthenticationFilter;
import com.jwt.stateless.javaloginjwt.filter.TokenBasedAuthorizationFilter;
import com.jwt.stateless.javaloginjwt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final long TOKEN_LIFETIME = 604_800_000; // That's 7 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SECRET = "ThisIsOurSecretKeyToSignOurTokens";

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .addFilter(new TokenBasedAuthenticationFilter(authenticationManager()))
                .addFilter(new TokenBasedAuthorizationFilter(authenticationManager()));

        http.authorizeRequests()
                .antMatchers("/rest/unsecure/**").permitAll()
                .antMatchers("/**").authenticated();
    }
}
