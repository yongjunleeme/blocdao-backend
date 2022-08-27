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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String uid;

    @Column(nullable = false, length = 20)
    private String nick_name;

    private String image_url;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String profile_link;

    @Column(nullable = false)
    private boolean is_withdrawal = false;

    @Column(nullable = true)
    private LocalDate data_withdrawal;

    @Builder.Default
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberStack> memberStacks = new ArrayList<MemberStack>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Project> projects = new ArrayList<Project>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<ProjectApplicant> projectApplicants= new ArrayList<ProjectApplicant>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<Comment>();

}
