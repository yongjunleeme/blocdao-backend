package com.blocdao.project.dto.project.response;

import com.blocdao.project.dto.projectStacks.response.ProjectStackResponseDto;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.ProjectStacks;
import com.blocdao.project.entity.Stacks;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.blocdao.project.service.TempStacksService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResponseDto {
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endTime;

    public PageResponseDto(Project project, TempStacksService tempStacksService){

        // 전체 프로젝트 중 개별 프로젝트에 접근하면서 각각 프로젝트의 Stacks를 response dto로 변환하기 위해 사용
        List<ProjectStacks> projectStackList = project.getProjectStacks();
        // 각각 dto로 변환하여 List에 담는 용도이다.
        List<ProjectStackResponseDto> projectStackResponseDtoList = new ArrayList<>();

        // 하나의 Project Entity에서 여러개의 스택들이 존재하기 때문에 각각의 repository return value에 접근해서
        // dto로 변환한 후 dto List에 add()하여 전체 responseDto에 dtoList를 추가한다.
        

        this.projectId = project.getId();
        this.address = project.getAddress();
        this.contact = project.getContact();
        this.createUid = project.getCreateUid();
        this.content = project.getContent();
        this.expectedStartDate = project.getExpectedStartDate();
        this.isOnline = project.getIsOnline();
        this.isRecruitment = project.getIsRecruitment();
        this.startTime = project.getStartTime();
        this.endTime = project.getEndTime();
        this.projectStackResponseDtoList = projectStackResponseDtoList;
        this.recruitmentNumber = project.getRecruitmentNumber();
        this.recruitmentType = project.getRecruitmentType();
        this.title = project.getTitle();

    }

}
