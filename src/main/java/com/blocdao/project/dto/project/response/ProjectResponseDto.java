package com.blocdao.project.dto.project.response;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.ProjectStacks;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProjectResponseDto {

    private Long id;
    private String title;
    private RecruitmentType recruitmentType;

    //총 모집인원
    @Column(nullable = false)
    private Integer recruitmentNumber;

    //온라인인지 오프라인인지
    @Column(nullable = false)
    private Boolean isOnline;

    //기간
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
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
}
