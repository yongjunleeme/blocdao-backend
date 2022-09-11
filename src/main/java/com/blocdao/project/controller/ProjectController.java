package com.blocdao.project.controller;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.dto.project.response.ProjectResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.service.ProjectService;
import com.blocdao.project.util.RequestUtil;
import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")

public class ProjectController {

    private final ProjectService projectService;

    private final UserDetailsService userDetailsService;

    @GetMapping()
    public ResponseEntity<Long> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto, Authentication authentication) {
        return projectService.createProject(projectRequestDto, (Member) authentication.getPrincipal());
    }

    //프로젝트 단일 조회
    @PostMapping("/{projectId}")
    public ResponseEntity<Project> projectDetail (@PathVariable @Valid Long projectId)

//    @PostMapping()
//    public ResponseEntity<Project> createProject(@RequestBody @Valid ProjectRequestDto projectRequestDto,
//                                                            @RequestHeader("Authorization") String header) {
//        header = RequestUtil.getAuthorizationToken(header);
//        UserDetails member = userDetailsService.loadUserByUsername(header);
//
//        return new ResponseEntity(projectService.createProject(projectRequestDto, member), HttpStatus.CREATED);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProject(@RequestParam String keyword) {
        return new ResponseEntity(projectService.findProject(keyword), HttpStatus.OK);
    }

    //프로젝트 상세페이지를 조회한다.
//    @GetMapping("/{projectId}")
//    public ResponseEntity<ProjectDetailResponseDto> projectDetail(@PathVariable Long projectId){
//        return new ResponseEntity(projectDetailService.projectDetail(projectId), HttpStatus.OK);
//    }
}
