package com.blocdao.project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStacks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_stack_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stacks stacks;

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.getProjectStacks().remove(this);
        }
        this.project = project;
        project.getProjectStacks().add(this);
    }

    public void setStacks(Stacks stacks) {
        if (this.stacks != null) {
            this.stacks.getMemberStacks().remove(this);
        }
        this.stacks = stacks;
        stacks.getProjectStacks().add(this);
    }

}
