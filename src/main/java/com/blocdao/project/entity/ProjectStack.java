package com.blocdao.project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectStack_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stack stack;

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.getProjectStacks().remove(this);
        }
        this.project = project;
        project.getProjectStacks().add(this);
    }

    public void setStack(Stack stack) {
        if (this.stack != null) {
            this.stack.getMemberStacks().remove(this);
        }
        this.stack = stack;
        stack.getProjectStacks().add(this);
    }

}
