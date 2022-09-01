package com.blocdao.project.service;

import com.blocdao.project.dto.member.response.MemberSignupResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberService {

    private final FirebaseAuth firebaseAuth;

    @Transactional
    public String signup(MemberSignupResponseDto memberSignupResponseDto) {

        FirebaseToken decodedToken;

        //token 추출
        try {
            String token = RequestUtil.getAuthorizationToken(memberSignupResponseDto.getToken());
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (FirebaseAuthException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }

        //member 생성
        Member member = Member.builder()
                .uid(decodedToken.getUid())
                .nickName(memberSignupResponseDto.getNickName())
                .imageUrl(memberSignupResponseDto.getImageUrl())
                .email(memberSignupResponseDto.getEmail())
                .phone(memberSignupResponseDto.getPhone())
                .profileLink(memberSignupResponseDto.getProfileLink())
                .build();
        return member.getNickName();
    }
}
