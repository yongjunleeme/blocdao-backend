package com.blocdao.project.dto.projectStacks.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackRequestDto {

    private Long stackId;

    private String classification;
}
