package com.blocdao.project.entity;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
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

    @Enumerated(value = EnumType.STRING)
    private RecruitmentType recruitmentType;

    private Integer recruitmentNumber;

    private Boolean isOnline;

    private Integer period;

    private LocalDate expectedStartDate;

    private String contact;

    private Boolean isRecruitment;

    private Integer view;

    private String address;

    private String title;

    private String createUid;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectStack> projectStacks = new ArrayList<ProjectStack>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;

    public Project(ProjectRequestDto projectRequestDto, Member member) {
        this.createUid = member.getUid();
        this.recruitmentType = projectRequestDto.getRecruitmentType();
        this.recruitmentNumber = projectRequestDto.getRecruitmentNumber();
        this.isOnline = projectRequestDto.getIsOnline();
        this.period = projectRequestDto.getPeriod();
        this.expectedStartDate = projectRequestDto.getExpectedStartDate();
        this.contact = projectRequestDto.getContact();
        this.title = projectRequestDto.getTitle();
        this.content = projectRequestDto.getContent();
        this.projectStacks = projectRequestDto.getProjectStacks();
    }
}
