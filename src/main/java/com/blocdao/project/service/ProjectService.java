package com.blocdao.project.service;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.dto.project.response.ProjectResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<Project> findAllProjectsPage(Pageable pageable) {
        return projectRepository.findAllProjects(pageable);
    }

    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto, Member member) {
        Project project = projectRequestDto.toEntity(projectRequestDto, member);

        // todo: projectStack save 점검
        Project savedProject = projectRepository.saveAndFlush(project);
        return new ProjectResponseDto(savedProject);
    }

}
