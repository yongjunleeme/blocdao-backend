package com.blocdao.project.service;

import com.blocdao.project.domain.member.Member;
import com.blocdao.project.domain.project.Project;
import com.blocdao.project.domain.project.ProjectRepository;
import com.blocdao.project.domain.RecruitmentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

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
                .build();

        given(projectRepository.findAll()).willReturn(List.of(project));
    }

    @Test
    void getProjects() {
        List<Project> projects = projectService.getProjects();
        assertThat(projects).isNotEmpty();

        Project project = projects.get(0);
        assertThat(project.getId()).isEqualTo(PROJECT_ID);
    }
}
