package com.blocdao.project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_member_uid")
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_applicant_id")
    private ProjectApplicant projectApplicant;
}
