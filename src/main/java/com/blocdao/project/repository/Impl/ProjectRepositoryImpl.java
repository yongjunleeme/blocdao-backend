package com.blocdao.project.repository.Impl;

import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.custom.ProjectCustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import javax.persistence.EntityManager;
import java.util.List;

import static com.blocdao.project.entity.QProject.project;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    private Querydsl getQuerydsl(Class clazz) {    // 1)
        PathBuilder<Project> builder = new PathBuilderFactory().create(clazz);
        return new Querydsl(entityManager, builder);
    }

    public <T> PageImpl<T> getPageImpl(Pageable pageable, JPQLQuery<T> query, Class clazz) {    // 2)
        long totalCount = query.fetchCount();
        List<T> results = getQuerydsl(clazz).applyPagination(pageable, query).fetch();

        return new PageImpl<>(results, pageable, totalCount);
    }

    @Override
    public Page<Project> findAllBySearchOption(Pageable pageable, String projectType, String startTime, String title) {
        JPQLQuery<Project> query = queryFactory.selectFrom(project)
                .where(eqProjectType(projectType), eqStartTime(startTime), eqProjectName(title));
        if(getPageImpl(pageable, query, Project.class).getTotalElements() == 0){
            throw new CustomException(ErrorCode.NOT_FOUND_PROJECT);
        }
        return getPageImpl(pageable, query, Project.class);
    }

    private Predicate eqProjectName(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        return project.title.eq(title);
    }

    private BooleanExpression eqProjectType(String recruitmentType) {
        if (recruitmentType == null || recruitmentType.toString().isEmpty()) {
            return null;
        }
        return project.recruitmentType.eq(RecruitmentType.valueOf(recruitmentType));
    }

    private BooleanExpression eqStartTime(String startTime) {
        if (startTime == null || startTime.isEmpty()) {
            return null;
        }
        return project.startTime.eq(startTime);
    }
}
