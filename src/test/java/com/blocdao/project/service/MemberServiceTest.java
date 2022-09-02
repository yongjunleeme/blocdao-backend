package com.blocdao.project.service;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {


    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void signup() {
        //given
        MemberSignupRequestDto memberSignupRequestDto = MemberSignupRequestDto.builder()
                .nickName("test_nickName")
                .imageUrl("test_imageUrl")
                .email("test_email")
                .phone("test_phone")
                .profileLink("test_profileLink")
                .isWithdrawal(false)
                .dataWithdrawal(null)
                .build();

        String header = "Bearer 1";
        //when
        String signupUid = memberService.signup(memberSignupRequestDto, header);

        //then
        Assertions.assertThat(signupUid).isEqualTo("test_nickName");
    }
}
