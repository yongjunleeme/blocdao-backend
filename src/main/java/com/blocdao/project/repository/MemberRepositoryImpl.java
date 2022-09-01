package com.blocdao.project.repository;

import com.blocdao.project.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    public MemberRepositoryImpl() {
        super(Member.class);
    }


}
