package com.blocdao.project.service;

import com.blocdao.project.entity.Project;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProjectSpec {

    public static Specification<Project> searchProject(final String keyword) {
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                if (StringUtils.isEmpty(keyword)) return null;

                return builder.like(root.get("title"), "%" + keyword + "%");
            }
        };
    }
}
