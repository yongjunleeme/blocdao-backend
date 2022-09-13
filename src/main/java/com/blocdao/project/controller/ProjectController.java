package com.blocdao.project.controller;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.dto.projectDetail.response.ProjectDetailResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.blocdao.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")

public class ProjectController {

    private final ProjectService projectService;

    //프로젝트 만들기
    @PostMapping()
    public ResponseEntity<Long> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto,
                                              Authentication authentication) {
        return projectService.createProject(projectRequestDto, (Member) authentication.getPrincipal());
    }

    //프로젝트 전체 조회 페이징 처리
    @GetMapping()
    public Page<Project> findProjects(Pageable pageable,
                                      @RequestParam(value = "projectType", required = false) RecruitmentType projectType,
                                      @RequestParam(value = "projectDay", required = false) String startTime,
                                      @RequestParam(value = "projectName", required = false) String title) {
        Page<Project> searchResult = projectService.findByAllCategory(pageable, projectType, startTime, title);

        return searchResult;
    }

    //프로젝트 단일 조회
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponseDto> projectDetail(@PathVariable @Valid Long projectId) {
        return projectService.projectDetail(projectId);
    }

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

    // 배포 서버 테스트용 프로젝트 전체 조회
    //프로젝트 상세페이지를 조회한다.
//    @GetMapping("/{projectId}")
//    public ResponseEntity<ProjectDetailResponseDto> projectDetail(@PathVariable Long projectId){
//        return new ResponseEntity(projectDetailService.projectDetail(projectId), HttpStatus.OK);
//    }
}
