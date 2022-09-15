package com.blocdao.project.controller;

import com.blocdao.project.dto.member.request.MemberSingupRequestDto;
import com.blocdao.project.dto.member.response.MemberResponseDto;
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

    //회원가입
    @PostMapping()
    public ResponseEntity<MemberResponseDto> signup(@Valid @RequestBody MemberSingupRequestDto memberSingupRequestDto,
                                                    @RequestHeader("Authorization") String header) {

        activeProfile = environment.getActiveProfiles()[0];

        if(activeProfile.equals("local")) {
            return memberService.signupMock(memberSingupRequestDto, header);
        } else {
            return memberService.signup(memberSingupRequestDto, header);
        }
    }

    // 로그인은 토큰만 확인하면 됩니다.
    @GetMapping()
    public ResponseEntity<String> login(Authentication authentication) {
        return memberService.login((Member) authentication.getPrincipal());
    }

    //내 member를 출력한다.
    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMember(Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        return ResponseEntity.ok(new MemberResponseDto(member));
    }

    //profile

    //내 프로필을 확인한다.
    @GetMapping("/project")
    public List<Project> project(Authentication authentication) {
        return memberService.project((Member) authentication.getPrincipal());
    }
}