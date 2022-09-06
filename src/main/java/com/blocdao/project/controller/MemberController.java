package com.blocdao.project.controller;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.dto.member.response.MemberFindMyResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.service.MemberService;
import com.blocdao.project.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberController {

    private String activeProfile;

    private final MemberService memberService;
    private final Environment environment;
    private final UserDetailsService userDetailsService;
    @PostMapping("/signup")
    public String signup(@Valid @RequestBody MemberSignupRequestDto memberSignupResponseDto, @RequestHeader("Authorization") String header) {

        activeProfile = environment.getActiveProfiles()[0];

        if(activeProfile.equals("local")) {
            return memberService.signupMock(memberSignupResponseDto, header);
        } else {
            return memberService.signup(memberSignupResponseDto, header);
        }
    }

    // 로그인은 토큰만 확인하면 됩니다.
    @PostMapping("/login")
    public String login(Authentication authentication, @RequestHeader("Authorization") String header) {

        activeProfile = environment.getActiveProfiles()[0];

        if(activeProfile.equals("local")) {
            return memberService.loginMock((Member) authentication.getPrincipal());
        } else {
            return memberService.login((Member) authentication.getPrincipal());
        }
    }

    @GetMapping
    public MemberFindMyResponseDto findMy(@RequestHeader("Authorization") String header) {
        return memberService.findMy(header);
    }
}