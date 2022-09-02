package com.blocdao.project.controller;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody MemberSignupRequestDto memberSignupResponseDto, @RequestHeader("Authorization") String header) {
        return memberService.signup(memberSignupResponseDto, header);
    }
}
