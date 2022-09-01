package com.blocdao.project.repository;

import com.blocdao.project.domain.ProjectApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectApplicantRepository extends JpaRepository<ProjectApplicant, Long> {
}
