package com.blocdao.project.entity;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
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

    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //제목
    @Column(nullable = false)
    private String title;

    //프로젝트 타임 스터디 인지 프로젝트 인지
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecruitmentType recruitmentType;

    //총 모집인원
    @Column(nullable = false)
    private Integer recruitmentNumber;

    //온라인인지 오프라인인지
    @Column(nullable = false)
    private Boolean isOnline;

    //기간
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    private String expectedStartDate;

    private String contact;

    private Boolean isRecruitment;

    private Integer view = 0;

    private String address;

    private String createUid;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectStacks> projectStacks = new ArrayList<>();

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getProjects().remove(this);
        }
        this.member = member;
        member.getProjects().add(this);
    }

    public void addProjectStacks(ProjectStacks projectStacks) {
        this.projectStacks.add(projectStacks);
        if (projectStacks.getProject() != this) {
            projectStacks.setProject(this);
        }
    }
}


