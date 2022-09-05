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
public class Stacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stack_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String classification;

    @Column
    private String image;

    @OneToMany(mappedBy = "stacks")
    private List<MemberStack> memberStacks = new ArrayList<>();

    @OneToMany(mappedBy = "stacks")
    private List<ProjectStack> projectStacks = new ArrayList<>();

    public void addMemberStacks(MemberStack memberStack) {
        this.memberStacks.add(memberStack);
        if (memberStack.getStacks() != this) {
            memberStack.setStacks(this);
        }
    }


}
