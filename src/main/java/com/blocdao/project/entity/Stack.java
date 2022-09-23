package com.blocdao.project.entity;

import com.blocdao.project.dto.stack.request.StackCreateRequestDto;
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
public class Stack {

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

    @OneToMany(mappedBy = "stack")
    private List<MemberStack> memberStacks = new ArrayList<>();

    @OneToMany(mappedBy = "stack")
    private List<ProjectStack> projectStacks = new ArrayList<>();

    public Stack(StackCreateRequestDto stackCreateRequestDto) {
        this.name = stackCreateRequestDto.getName();
        this.imageUrl = stackCreateRequestDto.getImageUrl();
    }
}
