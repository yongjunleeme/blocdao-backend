package com.blocdao.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 690485801L;

    public static final QMember member = new QMember("member1");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final DatePath<java.time.LocalDate> dataWithdrawal = createDate("dataWithdrawal", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath isWithdrawal = createBoolean("isWithdrawal");

    public final ListPath<MemberStacks, QMemberStacks> memberStacks = this.<MemberStacks, QMemberStacks>createList("memberStacks", MemberStacks.class, QMemberStacks.class, PathInits.DIRECT2);

    public final StringPath nickName = createString("nickName");

    public final StringPath phone = createString("phone");

    public final StringPath profileLink = createString("profileLink");

    public final ListPath<Project, QProject> projects = this.<Project, QProject>createList("projects", Project.class, QProject.class, PathInits.DIRECT2);

    public final StringPath uid = createString("uid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

