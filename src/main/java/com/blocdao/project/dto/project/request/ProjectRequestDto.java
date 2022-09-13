package com.blocdao.project.dto.project.request;

import com.blocdao.project.dto.projectStacks.request.ProjectStackRequestDto;
import com.blocdao.project.entity.*;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequestDto {
    //todo: Enum Validator
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

    @JsonProperty("projectStacks")
    private List<ProjectStackRequestDto> Stacks;

//    public Project toEntity(ProjectRequestDto projectRequestDto) {
//        return Project.builder()
//                ..build();
//    }

}
