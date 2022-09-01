package com.blocdao.project.repository;

import com.blocdao.project.entity.ProjectApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectApplicantRepository extends JpaRepository<ProjectApplicant, Long> {
}
