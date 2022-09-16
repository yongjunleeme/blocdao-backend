package com.blocdao.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStacks is a Querydsl query type for Stacks
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStacks extends EntityPathBase<Stacks> {

    private static final long serialVersionUID = 875757178L;

    public static final QStacks stacks = new QStacks("stacks");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final ListPath<MemberStacks, QMemberStacks> memberStacks = this.<MemberStacks, QMemberStacks>createList("memberStacks", MemberStacks.class, QMemberStacks.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<ProjectStacks, QProjectStacks> projectStacks = this.<ProjectStacks, QProjectStacks>createList("projectStacks", ProjectStacks.class, QProjectStacks.class, PathInits.DIRECT2);

    public QStacks(String variable) {
        super(Stacks.class, forVariable(variable));
    }

    public QStacks(Path<? extends Stacks> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStacks(PathMetadata metadata) {
        super(Stacks.class, metadata);
    }

}

