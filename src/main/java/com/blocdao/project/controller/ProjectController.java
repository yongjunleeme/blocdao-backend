package com.blocdao.project.controller;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.dto.project.response.ProjectResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<Project>> allProjects(Pageable pageable) {

        return ResponseEntity.ok(projectService.findAllProjectsPage(pageable));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(Authentication authentication,
                                                            @RequestBody @Valid ProjectRequestDto projectRequestDto) {

        Member member = ((Member) authentication.getPrincipal());
        ProjectResponseDto projectResponseDto = projectService.createProject(projectRequestDto, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponseDto);
    }
}
