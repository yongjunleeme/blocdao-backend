package com.blocdao.project.repository.Impl;

import com.blocdao.project.entity.Project;
import com.blocdao.project.repository.custom.ProjectCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.blocdao.project.entity.QProject.project;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Project> findAllProjects(Pageable pageable) {
        List<Project> content = queryFactory
                .selectFrom(project)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(project.createTime.desc())
                .fetch();

        Long total = queryFactory
                .select(project.count())
                .from(project)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
