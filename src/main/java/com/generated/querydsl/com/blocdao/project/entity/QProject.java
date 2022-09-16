package com.blocdao.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -1327968918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final StringPath contact = createString("contact");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath createUid = createString("createUid");

    public final StringPath endTime = createString("endTime");

    public final StringPath expectedStartDate = createString("expectedStartDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOnline = createBoolean("isOnline");

    public final BooleanPath isRecruitment = createBoolean("isRecruitment");

    public final QMember member;

    public final ListPath<ProjectStacks, QProjectStacks> projectStacks = this.<ProjectStacks, QProjectStacks>createList("projectStacks", ProjectStacks.class, QProjectStacks.class, PathInits.DIRECT2);

    public final NumberPath<Integer> recruitmentNumber = createNumber("recruitmentNumber", Integer.class);

    public final EnumPath<com.blocdao.project.entity.enums.RecruitmentType> recruitmentType = createEnum("recruitmentType", com.blocdao.project.entity.enums.RecruitmentType.class);

    public final StringPath startTime = createString("startTime");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProject(PathMetadata metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

