package com.blocdao.project.entity;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    private String recruitmentType;

    private Integer recruitmentNumber;

    private Boolean isOnline;

    private Integer period;

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


