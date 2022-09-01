package com.blocdao.project.dto.response;

import com.blocdao.project.entity.RecruitmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProjectResponseDto {

    //todo: 제목 추가

    private String nickName;
    private Long projectId;
    private Enum<RecruitmentType> recruitmentType;
    private int recruitmentNumber;
    private boolean isOnline;
    private int period;
    private LocalDate expectedStartDate;
    private boolean isRecruitment;
    private String address;
    private int comments;
    private int views;
    private List<StackResponseDto> projectStacks;
}
