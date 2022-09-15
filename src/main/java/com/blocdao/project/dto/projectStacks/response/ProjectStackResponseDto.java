package com.blocdao.project.dto.projectStacks.response;

import com.blocdao.project.entity.Stacks;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackResponseDto {

    private Long stackId;

    private String name;


    private String image;

    public ProjectStackResponseDto(Stacks stack){
        this.stackId = stack.getId();
        this.image = stack.getImageUrl();

    }
}
