package com.blocdao.project.service;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberService implements UserDetailsService {

    private final FirebaseAuth firebaseAuth;
    private final MemberRepository memberRepository;

    @Transactional
    public String signup(MemberSignupRequestDto memberSignupResponseDto, String header) {

        validateAlreadyRegistered(memberSignupResponseDto.getUid());

        FirebaseToken decodedToken;
        String token = RequestUtil.getAuthorizationToken(header);

        //token 추출
        try {
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
                .isWithdrawal(memberSignupResponseDto.getIsWithdrawal())
                .dataWithdrawal(null)
                .build();

        memberRepository.save(member);

        return member.getNickName();
    }

    // 유저의 정보를 불러와서 UserDetails로 반환해준다
    // spring security에서 사용자의 정보를 담는 인터페이스
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return memberRepository.findByUid(uid)
                .orElseThrow(()->{
                    throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");
                });
    }

    private void validateAlreadyRegistered(String uid) {
        Optional<Member> optionalMember = memberRepository.findByUid(uid);
        if (optionalMember.isPresent()) {
            throw new CustomException(ErrorCode.EXIST_MEMBER);
        }
    }

    //로그인
    public String login(String header) {
        FirebaseToken decodedToken;
        String token = RequestUtil.getAuthorizationToken(header);

        //token 추출
        try {
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (FirebaseAuthException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }

        Member member = memberRepository.findByUid(decodedToken.getUid()).get();

        return member.getNickName();
    }
}
