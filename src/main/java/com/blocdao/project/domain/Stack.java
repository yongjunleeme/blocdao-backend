package com.blocdao.project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stack_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String classification;

    @OneToMany(mappedBy = "stack", fetch = FetchType.LAZY)
    private List<MemberStack> memberStacks = new ArrayList<MemberStack>();

    @OneToMany(mappedBy = "stack", fetch = FetchType.LAZY)
    private List<ProjectStack> projectStacks = new ArrayList<ProjectStack>();
}
