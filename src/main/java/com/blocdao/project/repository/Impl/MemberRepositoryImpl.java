package com.blocdao.project.repository.Impl;

import com.blocdao.project.entity.Member;
import com.blocdao.project.repository.custom.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    public MemberRepositoryImpl() { super(Member.class); }
}
