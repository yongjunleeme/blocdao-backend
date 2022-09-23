package com.blocdao.project.dto.projectStacks.response;

import com.blocdao.project.entity.Stack;
import lombok.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackResponseDto {

    private String name;

    private String image;
}
