package com.blocdao.project.dto.projectStacks.response;

import com.blocdao.project.entity.enums.EnumStacks;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStackResponseDto {

    private Long stackId;

    private String name;

    @Enumerated(EnumType.STRING)
    private EnumStacks enumStacks;

    private String image;
}
