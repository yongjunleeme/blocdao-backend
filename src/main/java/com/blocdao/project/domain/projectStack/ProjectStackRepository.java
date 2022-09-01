package com.blocdao.project.domain.projectStack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStackRepository extends JpaRepository<ProjectStack, Long> {
}
