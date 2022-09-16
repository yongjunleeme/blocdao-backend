package com.blocdao.project.service;

import com.blocdao.project.dto.member.request.MemberSingupRequestDto;
import com.blocdao.project.dto.member.response.MemberProfileResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.MemberStacks;
import com.blocdao.project.entity.Stacks;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.repository.StacksRepository;
import com.blocdao.project.util.RequestUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final FirebaseAuth firebaseAuth;
    private final MemberRepository memberRepository;
    private final StacksRepository stacksRepository;

    @Transactional
    public ResponseEntity<String> signup(MemberSingupRequestDto memberResponseDto, String header) {

        String token = RequestUtil.getAuthorizationToken(header);
        FirebaseToken decodedToken = verifyToken(token);

        validateAlreadyRegistered(decodedToken.getUid());

        Member member = new Member(memberResponseDto, decodedToken.getUid());

        memberRepository.save(member);

        createMemberStacks(memberResponseDto.getStacks(), member);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(member.getNickName());
    }

    // 유저의 정보를 불러와서 UserDetails로 반환해준다
    @Transactional
    public ResponseEntity<String> signupMock(MemberSingupRequestDto memberResponseDto, String header) {

        String uid = RequestUtil.getAuthorizationToken(header);
        validateAlreadyRegistered(uid);

        Member member = new Member(memberResponseDto, uid);

        createMemberStacks(memberResponseDto.getStacks(), member);

        memberRepository.save(member);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(member.getNickName());
    }

    // spring security에서 사용자의 정보를 담는 인터페이스

    //로그인
    public ResponseEntity<String> login(Member member) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(member.getNickName());
    }

    //프로필 조회
    private void validateAlreadyRegistered(String uid) {
        Optional<Member> optionalMember = memberRepository.findById(uid);
        if (optionalMember.isPresent()) {
            throw new CustomException(ErrorCode.EXIST_MEMBER);
        }
    }

    private FirebaseToken verifyToken(String token) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);
            return decodedToken;
        } catch (FirebaseAuthException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return memberRepository.findById(uid)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");
                });
    }

    private void createMemberStacks(List<String> stackList, Member member) {
        stackList.forEach((stack)->{
            Stacks stacks = stacksRepository.findByName(stack)
                    .orElseThrow(() -> {
                        throw new CustomException(ErrorCode.NOT_FOUND_STACK);
                    });

            MemberStacks memberStacks = new MemberStacks();
            memberStacks.setMember(member);
            memberStacks.setStacks(stacks);
        });
    }

    public ResponseEntity<MemberProfileResponseDto> profile(Member principal) {
        MemberProfileResponseDto memberProfileResponseDto = new MemberProfileResponseDto(principal);

        return ResponseEntity
                .ok(memberProfileResponseDto);
    }
}
