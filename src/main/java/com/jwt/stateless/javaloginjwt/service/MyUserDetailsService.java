package com.jwt.stateless.javaloginjwt.service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class MyUserDetailsService implements UserDetailsService {
    private PasswordEncoder passwordEncoder;

    public MyUserDetailsService() {}

    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
        So this is where one must normally query the supporting database to check for the user’s
        existence and to load its details. For the sake of this tutorial I just hard-coded a test user’s
        details, but in a real situation one would probably use a DAO or a Repository instance,
        provided by means of the class’ constructor.
         */
        if (!username.equals("user")) {
            throw new UsernameNotFoundException("not found");
        }
        return new User("user", passwordEncoder.encode("password"), Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
