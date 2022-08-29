package com.blocdao.project.entity;

import com.blocdao.project.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    private String uid;

    @Column(nullable = false, length = 20)
    private String nickName;

    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    private String profileLink;

    @Column(nullable = false)
    private boolean isWithdrawal = false;

    @Column(nullable = true)
    private LocalDate dataWithdrawal;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberStack> memberStacks = new ArrayList<MemberStack>();

    @OneToMany(mappedBy = "member")
    private List<Project> projects = new ArrayList<Project>();

    @OneToMany(mappedBy = "member")
    private List<ProjectApplicant> projectApplicants= new ArrayList<ProjectApplicant>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<Comment>();
}
