package com.blocdao.project.domain;

import com.blocdao.project.domain.base.BaseTimeEntity;
import com.blocdao.project.domain.Member;
import com.blocdao.project.domain.RecruitmentType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RecruitmentType recruitmentType;

    private Integer recruitmentNumber;

    private Boolean isOnline;

    private Integer period;

    private LocalDate expectedStartDate;

    private String contact;

    private Boolean isRecruitment;

    private Integer view;

    private String address;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
