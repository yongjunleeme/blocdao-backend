package com.blocdao.project.security;

import com.blocdao.project.security.filter.FirebaseTokenFilter;
import com.blocdao.project.service.MemberService;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Profile("prod")
@Configuration
@EnableWebSecurity
public class ProdSecurityConfig extends SecurityConfig {
    public ProdSecurityConfig(MemberService memberService, FirebaseAuth firebaseAuth) {
        super(new FirebaseTokenFilter(memberService, firebaseAuth));
    }
}
