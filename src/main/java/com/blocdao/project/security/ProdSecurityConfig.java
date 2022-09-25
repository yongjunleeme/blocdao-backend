package com.blocdao.project.security;

import com.blocdao.project.security.filter.FirebaseCustomTokenFilter;
import com.blocdao.project.security.filter.FirebaseTokenFilter;
import com.blocdao.project.service.MemberService;
import com.blocdao.project.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.servlet.http.HttpServletRequest;

@Profile("prod")
@Configuration
@EnableWebSecurity
@Slf4j
//@Configuration
//@EnableWebSecurity
public class ProdSecurityConfig extends SecurityConfig {

    public ProdSecurityConfig(MemberService memberService, FirebaseAuth firebaseAuth) {
        super(new FirebaseCustomTokenFilter(memberService, firebaseAuth));
    }
}
