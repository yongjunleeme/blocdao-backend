package com.blocdao.project.service;

import com.blocdao.project.dto.comment.response.CommentListResponseDto;
import com.blocdao.project.dto.comment.response.CommentsResponseDto;
import com.blocdao.project.dto.project.response.ProjectDetailResponseDto;
import com.blocdao.project.dto.projectStacks.response.ProjectStackResponseDto;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.ProjectStack;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.ProjectRepository;
import com.blocdao.project.repository.ProjectStackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectDetailService {

    private final ProjectRepository projectRepository;

    private final ProjectStackRepository projectStackRepository;

    private final CommentService commentService;

    // 프로젝트 id를 통해 프로젝트 상세 페이지 정보를 조회한다.
    public ProjectDetailResponseDto projectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
        );

        List<ProjectStack> projectStacks =  projectStackRepository.findByProjectId(project);
        Optional<CommentListResponseDto> commentListResponseDto = Optional.ofNullable(commentService.getCommentList(projectId));

        ProjectDetailResponseDto projectDetailResponseDto = new ProjectDetailResponseDto(project, projectStacks, commentListResponseDto);

        return projectDetailResponseDto;
    }
}
