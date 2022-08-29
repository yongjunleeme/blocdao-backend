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
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    private String recruitmentType;

    private int recruitmentNumber;

    private boolean isOnline;

    private int period;

    private LocalDate expectedStartDate;

    private String contact;

    private boolean isRecruitment;

    private int view;

    private String address;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectStack> projectStacks = new ArrayList<ProjectStack>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectApplicant> projectApplicants = new ArrayList<ProjectApplicant>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();
}
