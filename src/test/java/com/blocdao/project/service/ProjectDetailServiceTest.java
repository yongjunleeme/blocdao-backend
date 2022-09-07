package com.blocdao.project.service;

import com.blocdao.project.BlocdaoApplicationTests;
import com.blocdao.project.entity.*;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.repository.ProjectRepository;
import com.blocdao.project.repository.ProjectStackRepository;
import com.blocdao.project.repository.StackRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Rollback(value = false)
public class ProjectDetailServiceTest extends BlocdaoApplicationTests {

    @Autowired
    ProjectDetailService projectDetailService;

    @Autowired
    ProjectStackRepository projectStackRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StackRepository stackRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    Project project;
    Member member;
    ProjectStack projectStack;
    Stacks stacks;
    Project savedProject;

    @BeforeEach
    void 객체_초기화(){

        List<Long> memberStacks = new ArrayList<>();
        memberStacks.add(1L);
        memberStacks.add(2L);

        member = Member.builder()
                .isWithdrawal(false)
                .dataWithdrawal(LocalDate.now())
                .uid("1")
                .email("whwj@naver.com")
                .imageUrl("asdf")
                .nickName("test")
                .phone("111")
                .profileLink("1")
                .build();

        log.info("맴버 생성");
        Member savedMember = memberRepository.save(member);

        stacks = Stacks.builder()
                .id(1L)
                .classification("spring")
                .image("image")
                .name("name")
                .build();

        Stacks stacks2 = Stacks.builder()
                .id(2L)
                .classification("react")
                .image("image")
                .name("name")
                .build();

        log.info("스택 객체 생성");

        Stacks savedStack = stackRepository.save(stacks);
        Stacks savedStack2 = stackRepository.save(stacks2);

        log.info("스택 레포 저장");

        project = Project.builder()
                .title("test title")
                .address("서울시 신림동")
                .id(1L)
                .view(1)
                .member(savedMember)
                .recruitmentType(RecruitmentType.STUDY)
                .recruitmentNumber(10)
                .period(10)
                .isOnline(true)
                .expectedStartDate(LocalDate.now())
                .content("content")
                .contact("contact")
                .createUid("123")
                .isRecruitment(true)
                .build();

        log.info("프로젝트 빌더");

        savedProject = projectRepository.saveAndFlush(project);

        log.info("프로젝트 생성");

        ProjectStack projectStack = ProjectStack.builder()
                .stacks(savedStack)
                .project(savedProject)
                .build();

        ProjectStack projectStack2 = ProjectStack.builder()
                .stacks(savedStack2)
                .project(savedProject)
                .build();

        log.info("프로젝트 스택 엔티티 생성");

        projectStackRepository.save(projectStack);
        projectStackRepository.save(projectStack2);

        log.info("프로젝트 스택 레포 생성");
    }
    @Test
    @DisplayName("ProjectStackRepository의 findByProjectId를 테스트한다.")
    public void expectSql(){

        log.info("-------------------------");

        assertThat(projectStackRepository.findByProjectId(project).get(0).getStacks().getClassification()).isEqualTo("spring");
        assertThat(projectStackRepository.findByProjectId(project).get(1).getStacks().getClassification()).isEqualTo("react");
    }
}