package com.blocdao.project.dto.project.request;

import com.blocdao.project.dto.projectStacks.request.ProjectStackRequestDto;
import com.blocdao.project.entity.*;
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
    private RecruitmentType recruitmentType;

    private Integer recruitmentNumber;

    private Boolean isOnline;

    private Integer period;

    private LocalDate expectedStartDate;

    private String contact;

    private String title;

    private String content;

    private String createUid;

    private List<ProjectStackRequestDto> stacksRequestDto;

    //private List<Comment> comments;

    public Project toEntity(ProjectRequestDto projectRequestDto, Member member) {
        return new Project(projectRequestDto, member);
    }

}
