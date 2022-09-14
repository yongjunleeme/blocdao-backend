package com.blocdao.project.entity;

import com.blocdao.project.entity.enums.EnumStacks;
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
    @Enumerated(EnumType.STRING)
    private EnumStacks enumStacks;

    @Column
    private String image;

    @OneToMany(mappedBy = "stacks")
    private List<MemberStacks> memberStacks = new ArrayList<>();

    @OneToMany(mappedBy = "stacks")
    private List<ProjectStacks> projectStacks = new ArrayList<>();

    public void addMemberStacks(MemberStacks memberStacks) {
        this.memberStacks.add(memberStacks);
        if (memberStacks.getStacks() != this) {
            memberStacks.setStacks(this);
        }
    }

    public void addProjectStacks(ProjectStacks projectStacks) {
        this.projectStacks.add(projectStacks);
        if (projectStacks.getStacks() != this) {
            projectStacks.setStacks(this);
        }
    }
}
