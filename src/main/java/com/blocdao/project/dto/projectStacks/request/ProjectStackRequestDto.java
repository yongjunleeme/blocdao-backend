package com.blocdao.project.dto.projectStacks.request;

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

//    public Stacks stackToEntity(ProjectStackRequestDto projectStackRequestDto){
//        Stacks stacks = Stacks.builder()
//                .id(projectStackRequestDto.getStackId())
//                .image(projectStackRequestDto.getImage())
//                .classification(projectStackRequestDto.getClassification())
//                .build();
//        return stacks;
//    }
}
