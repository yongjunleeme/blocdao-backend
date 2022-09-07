package com.blocdao.project.repository;

import com.blocdao.project.entity.Project;
import com.blocdao.project.repository.custom.ProjectCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>,
        ProjectCustomRepository,
        JpaSpecificationExecutor {
}
