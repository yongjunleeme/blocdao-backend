package com.blocdao.project.service;

import com.blocdao.project.dto.member.request.MemberSignupRequestDto;
import com.blocdao.project.dto.member.response.MemberFindMyResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.MemberStack;
import com.blocdao.project.entity.Stacks;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.repository.MemberStackRepository;
import com.blocdao.project.repository.StackRepository;
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
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final FirebaseAuth firebaseAuth;
    private final MemberRepository memberRepository;
    private final MemberStackRepository memberStackRepository;
    private final StackRepository stackRepository;

    @Transactional
    public String signup(MemberSignupRequestDto memberSignupResponseDto, String header) {

        String token = RequestUtil.getAuthorizationToken(header);

        //token 추출
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);

            validateAlreadyRegistered(decodedToken.getUid());

            //member 생성
            Member member = Member.builder()
                    .uid(decodedToken.getUid())
                    .nickName(memberSignupResponseDto.getNickName())
                    .imageUrl(memberSignupResponseDto.getImageUrl())
                    .email(memberSignupResponseDto.getEmail())
                    .phone(memberSignupResponseDto.getPhone())
                    .profileLink(memberSignupResponseDto.getProfileLink())
                    .build();

            memberSignupResponseDto.getMemberStacks().forEach(
                    StackId -> {
                        Stacks stacks = stackRepository.findById(StackId)
                                .orElseThrow(()->{
                                    throw new CustomException(ErrorCode.NOT_FOUND_STACK);
                                });
                        MemberStack memberStack = new MemberStack();

                        memberStack.setMember(member);
                        memberStack.setStacks(stacks);

                        member.addMemberStacks(memberStack);
                    }
            );

            memberRepository.save(member);

            return member.getNickName();

        } catch (FirebaseAuthException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    // 유저의 정보를 불러와서 UserDetails로 반환해준다
    @Transactional
    public String signupMock(MemberSignupRequestDto memberSignupResponseDto, String header) {

        String token = RequestUtil.getAuthorizationToken(header);

        validateAlreadyRegistered(token);

        //member 생성
        Member member = Member.builder()
                .uid(token)
                .nickName(memberSignupResponseDto.getNickName())
                .imageUrl(memberSignupResponseDto.getImageUrl())
                .email(memberSignupResponseDto.getEmail())
                .phone(memberSignupResponseDto.getPhone())
                .profileLink(memberSignupResponseDto.getProfileLink())
                .build();

        memberRepository.save(member);

        return member.getNickName();
    }
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
    public String login(Member member) {
        return member.getNickName();
    }

    //로그인
    public String loginMock(Member member) {
        return member.getNickName();
    }

    private String verifyToken(String token) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);
            return decodedToken.getUid();
        } catch (FirebaseAuthException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    public MemberFindMyResponseDto findMy(String header) {

        String token = RequestUtil.getAuthorizationToken(header);

        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);
            Member member = memberRepository.findByUid(decodedToken.getUid()).get();

            MemberFindMyResponseDto memberFindMyResponseDto = MemberFindMyResponseDto.builder()
                    .nickName(member.getNickName())
                    .email(member.getEmail())
                    .imageUrl(member.getImageUrl())
                    .phone(member.getPhone())
                    .profileLink(member.getProfileLink())
                    .build();

            return memberFindMyResponseDto;

        } catch (FirebaseAuthException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }

    }
}