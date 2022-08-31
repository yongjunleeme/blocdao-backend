package com.blocdao.project.controller;

import com.blocdao.project.entity.Project;
import com.blocdao.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<Project> list() {
        return projectService.getProjects();
    }
}
