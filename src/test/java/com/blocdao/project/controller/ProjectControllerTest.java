package com.blocdao.project.controller;

import com.blocdao.project.dto.project.request.ProjectRequestDto;
import com.blocdao.project.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class ProjectControllerTest {

    private static final String UID = "dy6dEPfvXEhG2lK0bgulLOIt2As1";
    private static final String NICK_NAME = "nick_name";
    private static final String IMAGE_URL = "www.image.com";
    private static final String EMAIL = "email@blocdao.com";
    private static final String PHONE = "010-0000-0000";
    private static final String PROFILE_LINK = ".";
    private static final Boolean IS_WITHDRAWAL = false;

    private static final Long PROJECT_ID = 1L;
    private static final Integer RECRUITMENT_NUMBER = 1;

    private static final RecruitmentType RECRUITMENT_TYPE = RecruitmentType.PROJECT;
    private static final Boolean IS_ONLINE = false;
    private static final Integer PERIOD = 1;
    private static final LocalDate EXPECTED_START_DATE = LocalDate.now();
    private static final String CONTACT = "yy@mail.com";
    private static final Boolean IS_RECRUITMENT = false;
    private static final Integer VIEW = 1;
    private static final String ADDRESS = "address";
    private static final String TITLE = "title";
    private static final String PROJECT_CONTENT = "text";

    private static final Long PROJECT_STACK_ID = 1L;
    private static final String CONTENT = "text";

    private static final Long STACK_ID = 1L;
    private static final String STACK_CLASSIFICATION = "spring";
    private static final String STACK_IMAGE = "image";

    List<ProjectStack> projectStacks = new ArrayList<ProjectStack>();

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();

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
                .content(PROJECT_CONTENT)
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

        projectStacks.add(projectStack);
    }

    @Test
    @DisplayName("create")
    void createProject() throws Exception {
        String startDate = "2022-10-01";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expectedStartDate = LocalDate.parse(startDate, format);

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setCreateUid(UID);
        projectRequestDto.setRecruitmentType(RECRUITMENT_TYPE);
        projectRequestDto.setRecruitmentNumber(RECRUITMENT_NUMBER);
        projectRequestDto.setIsOnline(IS_ONLINE);
        projectRequestDto.setPeriod(PERIOD);
        projectRequestDto.setExpectedStartDate(expectedStartDate);
        projectRequestDto.setContact(CONTACT);
        projectRequestDto.setTitle(TITLE);
        projectRequestDto.setContent(CONTENT);
        projectRequestDto.setProjectStacks(projectStacks);

        String project = objectMapper.writeValueAsString(projectRequestDto);

        ResultActions resultActions = mockMvc.perform(
                        post("/projects")
                                .header("Authorization", "Bearer " + UID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(project)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        //todo: 인증
        resultActions.toString();
//                .andExpect(status().isCreated());
    }

}
