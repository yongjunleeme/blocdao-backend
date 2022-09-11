package com.blocdao.project.service;

import com.blocdao.project.dto.member.request.MemberRequestDto;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.repository.StackRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WebAppConfiguration
@Transactional
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StackRepository stackRepository;

    //회원 가입
    @Test
    @Profile("local")
    void localSignup() {



        //given
        List<Long> memberStacks = new ArrayList<>();
        memberStacks.add(1L);
        memberStacks.add(2L);

        MemberRequestDto memberSignupRequestDto = MemberRequestDto.builder()
                .nickName("test_nickName")
                .imageUrl("test_imageUrl")
                .email("test_email")
                .phone("test_phone")
                .profileLink("test_profileLink")
                .memberStacks(memberStacks)
                .build();

        String header = "Bearer test_uid1";

        //when
        ResponseEntity<String> saveMemberNickName = memberService.signupMock(memberSignupRequestDto,header);

        //then
        Assertions.assertThat("test_nickName").isEqualTo(saveMemberNickName.getBody());

    }
}