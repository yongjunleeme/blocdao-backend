package com.blocdao.project.service;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    private static final String UID = "dy6dEPfvXEhG2lK0bgulLOIt2As1";
    private static final String NICK_NAME = "nick_name";
    private static final String IMAGE_URL = "www.image.com";
    private static final String EMAIL = "email@blocdao.com";
    private static final String PHONE = "010-0000-0000";
    private static final String PROFILE_LINK = ".";
    private static final Boolean IS_WITHDRAWAL = false;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    //회원 가입
    @Test
    @Profile("local")
    void signup() throws Exception {
        //given
        MemberSignupRequestDto memberSignupRequestDto = MemberSignupRequestDto.builder()
                .nickName(NICK_NAME)
                .imageUrl(IMAGE_URL)
                .email(EMAIL)
                .phone(PHONE)
                .profileLink(PROFILE_LINK)
                .build();

        String header = "Bearer test_uid1";

        //when
        String saveMemberNickName = memberService.signupMock(memberSignupRequestDto,header);

        //then
        Assertions.assertThat(NICK_NAME).isEqualTo(saveMemberNickName);
    }

    //로그인
    @Test
    @Profile("local")
    void login() throws Exception {
        //given
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();

        Member member = Member.builder()
                .uid(UID)
                .nickName(NICK_NAME)
                .imageUrl(IMAGE_URL)
                .email(EMAIL)
                .phone(PHONE)
                .profileLink(PROFILE_LINK)
                .build();

        memberRepository.save(member);

        //when
        //then
        ResultActions resultActions = mockMvc.perform(
                        post("/api/member/login")
                                .header("Authorization", "Bearer clodwi")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        //todo : 인증
        resultActions.toString();
    }
}