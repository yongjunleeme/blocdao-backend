package com.blocdao.project.controller;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.dto.project.response.ProjectResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.service.ProjectService;
import com.blocdao.project.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.blocdao.project.entity.QMember.member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    private final MemberRepository memberRepository;

    private final UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<Page<Project>> allProjects(Pageable pageable) {

        return ResponseEntity.ok(projectService.findAllProjectsPage(pageable));
    }

/*    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(Authentication authentication,
                                                            @RequestBody @Valid ProjectRequestDto projectRequestDto) {

        Member member = ((Member) authentication.getPrincipal());
        ProjectResponseDto projectResponseDto = projectService.createProject(projectRequestDto, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponseDto);
    }*/

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody @Valid ProjectRequestDto projectRequestDto,
                                                            @RequestHeader("Authorization") String header) {
        header = RequestUtil.getAuthorizationToken(header);
        UserDetails member = userDetailsService.loadUserByUsername(header);

        return new ResponseEntity(projectService.createProject(projectRequestDto, member), HttpStatus.CREATED);
    }

    @PostMapping
    @RequestMapping("/test")
    public ResponseEntity<ProjectResponseDto> testProject(@RequestBody ProjectRequestDto projectRequestDto,
                                                          @RequestHeader("Authorization") String header) {
        header = RequestUtil.getAuthorizationToken(header);
        Member member = memberRepository.findByUid(header).orElseThrow();

        UserDetails user = userDetailsService.loadUserByUsername(header);//user? id 를 통해 회원 엔티티 조회
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());//인증 객체 생성
        SecurityContextHolder.getContext().setAuthentication(authentication);//securityContextHolder 에 인증 객체 저장

        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        Member member1 = (Member) authentication1.getPrincipal();

        return new ResponseEntity(member1, HttpStatus.OK);
        //ProjectResponseDto projectResponseDto = projectService.createProject(projectRequestDto, member);

    }
}
