package com.blocdao.project.dto.project.request;

import com.blocdao.project.dto.projectStacks.request.ProjectStackRequestDto;
import com.blocdao.project.entity.*;
import com.blocdao.project.entity.enums.RecruitmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {
    //todo: Enum Validator
    private String recruitmentType;

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

    private List<Long> Stacks;



}
