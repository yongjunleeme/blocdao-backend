package com.blocdao.project.dto.projectStacks.response;

import com.blocdao.project.entity.Stack;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackResponseDto {

    private Long stackId;

    private String name;


    private String image;

    public ProjectStackResponseDto(Stack stack){
        this.stackId = stack.getId();
        this.image = stack.getImageUrl();

    }
}
