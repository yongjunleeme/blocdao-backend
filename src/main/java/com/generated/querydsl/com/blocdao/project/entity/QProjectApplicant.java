package com.blocdao.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectApplicant is a Querydsl query type for ProjectApplicant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectApplicant extends EntityPathBase<ProjectApplicant> {

    private static final long serialVersionUID = -1291458376L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectApplicant projectApplicant = new QProjectApplicant("projectApplicant");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QProject project;

    public final QProjectMember projectMember;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QProjectApplicant(String variable) {
        this(ProjectApplicant.class, forVariable(variable), INITS);
    }

    public QProjectApplicant(Path<? extends ProjectApplicant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectApplicant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectApplicant(PathMetadata metadata, PathInits inits) {
        this(ProjectApplicant.class, metadata, inits);
    }

    public QProjectApplicant(Class<? extends ProjectApplicant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.projectMember = inits.isInitialized("projectMember") ? new QProjectMember(forProperty("projectMember"), inits.get("projectMember")) : null;
    }

}

