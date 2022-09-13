package com.blocdao.project.repository.Impl;

import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.blocdao.project.repository.custom.ProjectCustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.blocdao.project.entity.QProject.project;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectCustomRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Project> findAllBySearchOption(Pageable pageable, RecruitmentType projectType, String startTime, String title) {
        JPQLQuery<Project> query = queryFactory.selectFrom(project)
                .where(eqProjectType(projectType), eqStartTime(startTime), eqProjectName(title));
        return null;
    }

    private Predicate eqProjectName(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        return project.title.eq(title);
    }

    private BooleanExpression eqProjectType(RecruitmentType recruitmentType) {
        if (recruitmentType == null || recruitmentType.toString().isEmpty()) {
            return null;
        }
        return project.recruitmentType.eq(recruitmentType);
    }

    private BooleanExpression eqStartTime(String startTime) {
        if (startTime == null || startTime.isEmpty()) {
            return null;
        }
        return project.startTime.eq(startTime);
    }
}
