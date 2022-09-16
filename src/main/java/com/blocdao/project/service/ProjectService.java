package com.blocdao.project.service;

import com.blocdao.project.dto.comment.response.CommentListResponseDto;
import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.dto.project.response.PageResponseDto;
import com.blocdao.project.dto.projectDetail.response.ProjectDetailResponseDto;
import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.entity.ProjectStacks;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.repository.ProjectRepository;
import com.blocdao.project.repository.ProjectStackRepository;
import com.blocdao.project.repository.StacksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.blocdao.project.service.ProjectSpec.searchProject;
import static org.springframework.data.jpa.domain.Specification.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Lazy
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final StacksRepository stacksRepository;

    private final ProjectStackRepository projectStackRepository;

    private final CommentService commentService;

    private final MemberRepository memberRepository;

    private final TempStacksService tempStacksService;

    /*

     현재 프로젝트 데이터 생성 및 project_stack 테이블에 데이터 입력까지 확인했음.
     테스트 코드를 사용하는 것도 좋지만 postman으로 데이터 전송 후 h2에서 데이터 들어갔는지 까지 확인하는게 좋음.
     postman없이 사용하려면 Controller단으로 Mocking없이 테스트 들어가야함.

    포스트맨 링크에서 회원가입 api 전송 후 h2접속 후 Stack테이블에 stacks id : 1, 2로 데이터 생성
    -> 이후 포스트맨에서 게시글 생성 api 전송하면 h2 테이블에 제대로 데이터들이 들어가는 것을 확인할 수 있고

    회원가입의 경우 uid값이 requestBody가 아닌 Authorization: Bearer uid를 통해 들어감
    Swagger로도 테스트 해보고 싶다면 Authorize 정보에 Bearer 123을 넣고 실행하면 된다.
    또한 게시글 생성 시에 회원가입 api 전송시 넣었던 Bearer uid값이 없으면 시큐리티 필터쪽에서
    userDetailsService.loadUserByUsername(header)가 없기 때문에 오류가 남.
    현재 validation 추가를 하진않았지만 게시글 수정 및 삭제시엔 헤더의 토큰값과 Project 테이블의 생성자 id를 확인해서
    validation하는 것이 필요함.

    작업을 하면서 어려웠던 부분이 일단 8기 팀에서 매개변수로 Authentication authentication을 받았는데
    Api 요청시마다 필터쪽에서 확인을 하므로 굳이 필요가 없는 것 같고, post 전송 시 어떤 형식으로 데이터를 땡겨오는지 알 수 없어서
    postman으로 테스트가 안됨. 화요일날 질문 후에 필요하다면 작업 후에 사용하고 일단 8기팀의 필터구조도 커스텀이 들어갔기때문에
    목적과 사용법을 알아야 쓸듯.

    두번째로 현재 작성한 Entity와 Dto로 Swagger에서 기본으로 뜨는 json형식이 api명세서와 너무 달랐음.
    이유는 dto에서 맞춰진 형식대로 나타나는데 아래 코드에서 ProjectStacks Entity 클래스가 project, stacks 엔티티와
    연관관계가 맺어있어 불필요한 데이터까지 나오게됨 따라서 stacksRequestDto라는 stack추가에 필요한 dto를 생성하고
    이후 아래 참고 1에서 따로 재처리하였음.

    private List<ProjectStacks> projectStacks;

    public Project toEntity(ProjectRequestDto projectRequestDto, Member member) {
        return new Project(projectRequestDto, member);
    }

     */
    @Transactional
    public ResponseEntity<Long> createProject(ProjectRequestDto projectRequestDto, Member memberParam) {

        Member member = memberRepository.findById(memberParam.getUid()).orElseThrow();

        Project project = Project.builder()
                .recruitmentType(projectRequestDto.getRecruitmentType())
                .recruitmentNumber(projectRequestDto.getRecruitmentNumber())
                .isOnline(projectRequestDto.getIsOnline())
                .startTime(projectRequestDto.getExpectedStartDate())
                .endTime(projectRequestDto.getExpectedEndDate())
                .expectedStartDate(projectRequestDto.getExpectedStartDate())
                .contact(projectRequestDto.getContact())
                .isRecruitment(projectRequestDto.getIsRecruitment())
                .address(projectRequestDto.getAddress())
                .title(projectRequestDto.getTitle())
                .content(projectRequestDto.getContent())
                .createUid(member.getUid())
                .member(member)
                .build();

        projectRepository.save(project);

//        projectRequestDto.getStacks().forEach(
//                stackId -> {
//                    Stacks stacks = stackRepository.findByEnumStacks(stackId); //REACT
//
//                    //ProjectStacks projectStacks = new ProjectStacks();
//
//                    //projectStacks.setProject(project);
//                    //projectStacks.setStacks(stacks);
//
//                    ProjectStacks projectStacks = ProjectStacks.builder()
//                            .project(project)
//                            .stacks(stacks)
//                            .build();
//
//                    projectStackRepository.save(projectStacks);
//                }
//        );

        //project.setMember(member);

        // todo: projectStack save 점검
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(project.getId());
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow();
    }

    public List<Project> findProject(String keyword) {
        List<Project> result = projectRepository.findAll(
                (Sort) where(searchProject(keyword)));

        return result;
    }

    // 프로젝트 id를 통해 프로젝트 상세 페이지 정보를 조회한다.
    public ResponseEntity<ProjectDetailResponseDto> projectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
        );

        List<ProjectStacks> projectStacks =  projectStackRepository.findByProjectId(project);
        Optional<CommentListResponseDto> commentListResponseDto = Optional.ofNullable(commentService.getCommentList(projectId));

        ProjectDetailResponseDto projectDetailResponseDto = new ProjectDetailResponseDto(project, projectStacks, commentListResponseDto);

        return new ResponseEntity<>(projectDetailResponseDto, HttpStatus.FOUND);
    }

    public Page<PageResponseDto> findByAllCategory(Pageable pageable, String projectType, String startTime, String title) {
        Page<Project> projects = projectRepository.findAllBySearchOption(pageable, projectType, startTime, title);

        Page<PageResponseDto> pageResponseDtoPage = projects.map(project ->
                new PageResponseDto(project, tempStacksService));

        return pageResponseDtoPage;
    }

    public Page<PageResponseDto> getAllPageProjects(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);

        Page<PageResponseDto> pageResponseDtoPage = projects.map(project ->
                new PageResponseDto(project, tempStacksService));

        return pageResponseDtoPage;
    }
}
