package com.blocdao.project.dto.stack.response;

import com.blocdao.project.entity.Stack;
import lombok.Data;

@Data
public class StackFindStacksResponseDto {
    private Long id;
    private String name;
    private String imageUrl;

    public StackFindStacksResponseDto(Stack stack) {
        this.id = stack.getId();
        this.name = stack.getName();
        this.imageUrl = stack.getImageUrl();
    }
}
