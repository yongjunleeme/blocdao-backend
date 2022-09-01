package com.blocdao.project.repository;

import com.blocdao.project.domain.ProjectStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStackRepository extends JpaRepository<ProjectStack, Long> {
}
