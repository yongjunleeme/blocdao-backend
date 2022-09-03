package com.blocdao.project.entity;

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

    private String name;

    private String classification;

    private String image;

    @OneToMany(mappedBy = "stack", fetch = FetchType.LAZY)
    private List<MemberStack> memberStacks = new ArrayList<>();

    @OneToMany(mappedBy = "stack", fetch = FetchType.LAZY)
    private List<ProjectStack> projectStacks = new ArrayList<>();
}
