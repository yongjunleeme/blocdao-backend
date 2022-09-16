package com.blocdao.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberStacks is a Querydsl query type for MemberStacks
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberStacks extends EntityPathBase<MemberStacks> {

    private static final long serialVersionUID = 1389785716L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberStacks memberStacks = new QMemberStacks("memberStacks");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QStacks stacks;

    public QMemberStacks(String variable) {
        this(MemberStacks.class, forVariable(variable), INITS);
    }

    public QMemberStacks(Path<? extends MemberStacks> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberStacks(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberStacks(PathMetadata metadata, PathInits inits) {
        this(MemberStacks.class, metadata, inits);
    }

    public QMemberStacks(Class<? extends MemberStacks> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.stacks = inits.isInitialized("stacks") ? new QStacks(forProperty("stacks")) : null;
    }

}

