package com.blocdao.project.dto.projectDetail.response;

import com.blocdao.project.dto.comment.response.CommentListResponseDto;
import com.blocdao.project.dto.projectStacks.response.ProjectStackResponseDto;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.ProjectStacks;
import com.blocdao.project.entity.enums.RecruitmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailResponseDto {

    private Long projectId;

    private RecruitmentType recruitmentType;

    private Integer recruitmentNumber;

    private Boolean isOnline;

    private Integer period;

    private String expectedStartDate;

    private String contact;

    private String createDate;

    private String updateDate;

    private Boolean isRecruitment;

    private String address;

    @JsonProperty("projectStacks")
    private List<ProjectStackResponseDto> projectStackResponseDtoList;

    @JsonProperty("comments")
    private Optional<CommentListResponseDto> commentsResponseDtoList;

    // todo 댓글 및 프로젝트 지원자 추가하기
    /*
    Request를 dto로 받아 entity로 전환할때는 직렬화와 역직렬화를 통해 보통 전달되기 때문에 큰 문제는 없다.
    하지만 Entity를 response dto로 전환하여 리턴할때는 보통 롬복의 자동 생성자 어노테이션을 사용하게되면
    서비스단에서 dto를 호출하는 생성자의 매개변수(Entity 자체가 들어가거나 List 타입이 같이 들어감)가 롬복이 자동으로 생성한 생성자와 다른 경우가 많고
    서비스단에서 빌더패턴(서비스코드가 길어짐)으로 생성한다면 서비스단의 코드 자체가 길어지기 때문에 Dto쪽에서 매개변수에 맞는 생성자를 생성해주고,
    변수의 값들이 변경돼서 여러 번 호출돼야하는 경우 추가적으로 매소드를 만들어서 사용한다.
     */
    public ProjectDetailResponseDto(Project project, List<ProjectStacks> projectStacks, Optional<CommentListResponseDto> commentListResponseDto){

        List<ProjectStackResponseDto> dtoList = new ArrayList<>();

        for(ProjectStacks projectStack : projectStacks){
            ProjectStackResponseDto projectStackResponseDto = ProjectStackResponseDto.builder()
                    .stackId(projectStack.getStacks().getId())
                    .enumStacks(projectStack.getStacks().getEnumStacks())
                    .image(projectStack.getStacks().getImage())
                    .name(projectStack.getStacks().getName())
                    .build();
            dtoList.add(projectStackResponseDto);
        }
        this.projectId = project.getId();
        this.recruitmentType = project.getRecruitmentType();
        this.recruitmentNumber = project.getRecruitmentNumber();
        this.isOnline = project.getIsOnline();
        this.period = project.getPeriod();
        this.expectedStartDate = project.getExpectedStartDate();
        this.contact = project.getContact();
        this.isRecruitment = project.getIsRecruitment();
        this.address = project.getAddress();
        this.projectStackResponseDtoList = dtoList;
        this.commentsResponseDtoList = commentListResponseDto ;
    }


    //데이터가 변경되어 response dto 호출이 여러 번 필요한 경우 생성자 이외에 매소드를 추가하여 사용한다.
    //예시용
    public static ProjectDetailResponseDto ofProjectDetailResponseDto(Project project, List<ProjectStacks> projectStacks){

        List<ProjectStackResponseDto> dtoList = new ArrayList<>();

        for(ProjectStacks projectStack : projectStacks){
            ProjectStackResponseDto projectStackResponseDto = ProjectStackResponseDto.builder()
                    .stackId(projectStack.getStacks().getId())
                    .enumStacks(projectStack.getStacks().getEnumStacks())
                    .image(projectStack.getStacks().getImage())
                    .name(projectStack.getStacks().getName())
                    .build();
            dtoList.add(projectStackResponseDto);
        }
        ProjectDetailResponseDto responseDto = ProjectDetailResponseDto.builder()
                .address(project.getAddress())
                .projectStackResponseDtoList(dtoList)
                .build();

        return responseDto;
    }
}