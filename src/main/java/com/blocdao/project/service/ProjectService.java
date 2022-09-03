package com.blocdao.project.service;

import com.blocdao.project.entity.Project;
import com.blocdao.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<Project> findAllProjectsPage(Pageable pageable) {
        return projectRepository.findAllProjects(pageable);
    }
}
