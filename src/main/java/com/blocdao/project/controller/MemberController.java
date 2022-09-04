package com.blocdao.project.controller;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberController {

    private String activeProfile;

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService,
                          Environment environments) {
        this.memberService = memberService;
        this.activeProfile = environments.getActiveProfiles()[0];
        log.info("activeProfile: {}", activeProfile);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody MemberSignupRequestDto memberSignupResponseDto, @RequestHeader("Authorization") String header) {

        if(activeProfile.equals("local")) {
            return memberService.signupMock(memberSignupResponseDto, header);
        } else {
            return memberService.signup(memberSignupResponseDto, header);
        }
    }

    // 로그인은 토큰만 확인하면 됩니다.
    @PostMapping("login")
    public String login(@RequestHeader("Authorization") String header) {

        if(activeProfile.equals("local")) {
            return memberService.loginMock(header);
        } else {
            return memberService.login(header);
        }
    }

}