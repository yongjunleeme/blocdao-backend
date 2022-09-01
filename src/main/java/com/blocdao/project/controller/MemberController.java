package com.blocdao.project.controller;

import com.blocdao.project.dto.member.response.MemberSignupResponseDto;
import com.blocdao.project.service.MemberService;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody MemberSignupResponseDto memberSignupResponseDto, @RequestHeader("Authorization") String header) {
        memberSignupResponseDto.setToken(header);
        return memberService.signup(memberSignupResponseDto);
    }
}
