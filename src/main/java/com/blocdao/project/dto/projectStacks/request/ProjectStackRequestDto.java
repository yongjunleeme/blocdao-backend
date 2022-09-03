package com.blocdao.project.dto.projectStacks.request;

import com.blocdao.project.entity.Stack;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackRequestDto {

    private Long stackId;

    private String name;

    private String classification;

    private String image;

    public Stack stackToEntity(ProjectStackRequestDto projectStackRequestDto){
        Stack stack = Stack.builder()
                .id(projectStackRequestDto.getStackId())
                .image(projectStackRequestDto.getImage())
                .classification(projectStackRequestDto.getClassification())
                .build();
        return stack;
    }
}
