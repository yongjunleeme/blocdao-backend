package com.blocdao.project.controller;

import com.blocdao.project.dto.member.request.MemberSaveRequestDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private String activeProfile;

    private final MemberService memberService;

    private final Environment environment;

    @PostMapping()
    public ResponseEntity<String> signup(@Valid @RequestBody MemberSaveRequestDto memberResponseDto, @RequestHeader("Authorization") String header) {

        activeProfile = environment.getActiveProfiles()[0];


        if(activeProfile.equals("local")) {
            return memberService.signupMock(memberResponseDto, header);
        } else {
            return memberService.signup(memberResponseDto, header);
        }
    }

    // 로그인은 토큰만 확인하면 됩니다.
    @GetMapping()
    public String login(Authentication authentication) {

        activeProfile = environment.getActiveProfiles()[0];

        if(activeProfile.equals("local")) {
            return memberService.loginMock((Member) authentication.getPrincipal());
        } else {
            return memberService.login((Member) authentication.getPrincipal());
        }
    }

    @GetMapping("/profile")
    public Member profile(Authentication authentication) {
        return memberService.profile((Member) authentication.getPrincipal());
    }

    @GetMapping("/project")
    public List<Project> project(Authentication authentication) {
        return memberService.project((Member) authentication.getPrincipal());
    }

//    @GetMapping("/")


}