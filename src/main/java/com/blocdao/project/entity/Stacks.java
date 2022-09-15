package com.blocdao.project.entity;

import com.blocdao.project.dto.stack.request.StacksCreateRequestDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "기술스택 이름은 필수 입니다.")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "기술스택 이미지는 필수 입니다.")
    @Column(unique = true, nullable = false)
    private String imageUrl;

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

    public Stacks(StacksCreateRequestDto stacksCreateRequestDto) {
        this.name = stacksCreateRequestDto.getName();
        this.imageUrl = stacksCreateRequestDto.getImageUrl();
    }
}
