package com.blocdao.project.repository.custom;

import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.enums.RecruitmentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectCustomRepository {
    Page<Project> findAllBySearchOption(Pageable pageable, String projectType, String projectDay, String title);
}
