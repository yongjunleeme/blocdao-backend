package com.blocdao.project.dto.response;

import com.blocdao.project.entity.ProjectStack;
import com.blocdao.project.entity.RecruitmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProjectResponseDto {

    private Long memberUid;
    private String nickName;
    private Long projectId;
    private Enum<RecruitmentType> recruitmentType;
    private Integer recruitmentNumber;
    private boolean isOnline;
    private Integer period;
    private LocalDate expectedStartDate;
    private Boolean isRecruitment;
    private String address;
    private Integer comments;
    private Integer views;
    private String title;
    private List<ProjectStack> projectStacks;
    private Integer commentCount;
}
