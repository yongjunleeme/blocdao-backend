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
    @Column(name = "projectMember_uid")
    private Long id;

    @OneToOne
    @JoinColumn(name = "projectApplicant_id")
    private ProjectApplicant projectApplicant;
}
