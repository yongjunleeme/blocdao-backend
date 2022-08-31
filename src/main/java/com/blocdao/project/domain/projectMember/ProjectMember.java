package com.blocdao.project.domain.projectMember;

import com.blocdao.project.domain.ProjectApplicant.ProjectApplicant;
import com.blocdao.project.domain.base.BaseTimeEntity;
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
    @Column(name = "project_member_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_applicant_id")
    private ProjectApplicant projectApplicant;
}
