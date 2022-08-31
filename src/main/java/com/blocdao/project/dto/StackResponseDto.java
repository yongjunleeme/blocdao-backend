package com.blocdao.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StackResponseDto {

    private Long id;
    private String name;
    private String classification;
    private String image;
}
