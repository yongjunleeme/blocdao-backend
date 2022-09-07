package com.blocdao.project.repository.Impl;

import com.blocdao.project.entity.Comment;
import com.blocdao.project.entity.QComment;
import com.blocdao.project.repository.custom.CommentCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport
        implements CommentCustomRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    public CommentRepositoryImpl() {
        super(QComment.class);
    }

    @Override
    public List<Comment> findAllByProjectId(Long projectId) {
        List<Comment> comments = queryFactory.selectFrom(QComment.comment)
                .where(checkNull(projectId))
                .orderBy(QComment.comment.createTime.asc()).fetch();
        return comments;
    }

    private BooleanExpression checkNull(Long projectId) {
        if (projectId == null) {
            return null;
        } else {
            return QComment.comment.project.id.eq(projectId);
        }
    }
}
