package com.blocdao.project.dto.projectStacks.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackResponseDto {

    private Long stackId;

    private String name;

    private String classification;

    private String image;
}
