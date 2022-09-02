package com.blocdao.project.security;

import com.blocdao.project.security.filter.TestTokenFilter;
import com.blocdao.project.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Profile("local")
@Configuration
//@EnableWebSecurity
@Slf4j
public class TestSecurityConfig extends SecurityConfig {
    public TestSecurityConfig(MemberService memberService) {
        super(new TestTokenFilter(memberService));

    }
}
