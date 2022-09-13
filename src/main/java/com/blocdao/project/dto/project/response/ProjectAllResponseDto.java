package com.blocdao.project.dto.project.response;

import com.blocdao.project.dto.projectStacks.response.ProjectStackResponseDto;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.enums.EnumStacks;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
public class ProjectAllResponseDto {
    private Long projectId;

    @Enumerated(EnumType.STRING)
    private RecruitmentType recruitmentType;

    private Integer recruitmentNumber;

    private Boolean isOnline;

    private Integer period;

    private String expectedStartDate;

    private String contact;

    private String title;

    private String content;

    private Boolean isRecruitment;

    private String address;

    private String createUid;

    @JsonProperty("stacks")
    private List<ProjectStackResponseDto> projectStackResponseDtoList;

}
