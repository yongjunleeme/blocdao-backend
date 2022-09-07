package com.blocdao.project.repository;

import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.ProjectStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectStackRepository extends JpaRepository<ProjectStack, Long> {

   @Query(value = "select distinct p from ProjectStack p " +
            "where p.project = :project")
    List<ProjectStack> findByProjectId(@Param("project") Project project);
}
