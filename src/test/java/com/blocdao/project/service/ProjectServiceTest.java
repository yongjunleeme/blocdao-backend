package com.blocdao.project.service;

import com.blocdao.project.entity.*;
import com.blocdao.project.repository.ProjectRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ProjectServiceTest {

    private ProjectService projectService;
    private final ProjectRepository projectRepository = mock(ProjectRepository.class);

    private static final String UID = "1";
    private static final String NICK_NAME = "nick_name";
    private static final String IMAGE_URL = "www.image.com";
    private static final String EMAIL = "email@blocdao.com";
    private static final String PHONE = "010-0000-0000";
    private static final String PROFILE_LINK = ".";
    private static final Boolean IS_WITHDRAWAL = false;

    private static final Long PROJECT_ID = 1L;
    private static final Integer RECRUITMENT_NUMBER = 1;
    private static final Boolean IS_ONLINE = false;
    private static final Integer PERIOD = 1;
    private static final LocalDate EXPECTED_START_DATE = LocalDate.now();
    private static final String CONTACT = "contact";
    private static final Boolean IS_RECRUITMENT = false;
    private static final Integer VIEW = 1;
    private static final String ADDRESS = "address";
    private static final String TITLE = "title";

    private static final Long PROJECT_STACK_ID = 1L;

    private static final Long COMMENT_ID = 1L;
    private static final String CONTENT = "text";

    private static final Long STACK_ID = 1L;
    private static final String STACK_CLASSIFICATION = "spring";
    private static final String STACK_IMAGE = "image";

    @BeforeEach
    void setUp() {
        projectService = new ProjectService(projectRepository);

        Member member = Member.builder()
                .uid(UID)
                .nickName(NICK_NAME)
                .imageUrl(IMAGE_URL)
                .email(EMAIL)
                .phone(PHONE)
                .profileLink(PROFILE_LINK)
                .isWithdrawal(IS_WITHDRAWAL)
                .build();

        Project project = Project.builder()
                .id(PROJECT_ID)
                .member(member)
                .recruitmentType(RecruitmentType.STUDY)
                .recruitmentNumber(RECRUITMENT_NUMBER)
                .isOnline(IS_ONLINE)
                .period(PERIOD)
                .expectedStartDate(EXPECTED_START_DATE)
                .contact(CONTACT)
                .isRecruitment(IS_RECRUITMENT)
                .view(VIEW)
                .address(ADDRESS)
                .title(TITLE)
                .build();

        Stack stack = Stack.builder()
                .id(STACK_ID)
                .classification(STACK_CLASSIFICATION)
                .image(STACK_IMAGE)
                .build();

        ProjectStack projectStack = ProjectStack.builder()
                .id(PROJECT_STACK_ID)
                .project(project)
                .stack(stack)
                .build();

        Comment comment = Comment.builder()
                .id(COMMENT_ID)
                .project(project)
                .member(member)
                .content(CONTENT)
                .build();

        List<ProjectStack> projectStacks = new ArrayList<ProjectStack>();
        projectStacks.add(projectStack);

        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment);

        project = Project.builder()
                .id(PROJECT_ID)
                .member(member)
                .recruitmentType(RecruitmentType.STUDY)
                .recruitmentNumber(RECRUITMENT_NUMBER)
                .isOnline(IS_ONLINE)
                .period(PERIOD)
                .expectedStartDate(EXPECTED_START_DATE)
                .contact(CONTACT)
                .isRecruitment(IS_RECRUITMENT)
                .view(VIEW)
                .address(ADDRESS)
                .title(TITLE)
                .projectStacks(projectStacks)
                .comments(comments)
                .build();

        PageRequest pageRequest = PageRequest.of(0, 1);

        List<Project> projectList = new ArrayList<>();
        projectList.add(project);
        Page<Project> projects = new PageImpl<>(projectList);

        given(projectRepository.findAllProjects(pageRequest)).willReturn(projects);
    }

    @Test
    void getProjects() {
        PageRequest pageRequest = PageRequest.of(0, 1);
        Page<Project> projects = projectService.findAllProjectsPage(pageRequest);

        assertThat(projects).isNotEmpty();
    }
}
