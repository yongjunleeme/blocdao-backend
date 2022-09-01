package com.blocdao.project.domain.projectStack;

import com.blocdao.project.domain.stack.Stack;
import com.blocdao.project.domain.project.Project;
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
    @Column(name = "project_stack_id")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stack stack;
}
