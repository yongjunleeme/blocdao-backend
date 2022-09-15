package com.blocdao.project.dto.stack.response;

import com.blocdao.project.entity.Stacks;
import lombok.Data;

@Data
public class StacksFindStacksResponseDto {
    private Long id;
    private String name;
    private String imageUrl;

    public StacksFindStacksResponseDto(Stacks stacks) {
        this.id = stacks.getId();
        this.name = stacks.getName();
        this.imageUrl = stacks.getImageUrl();
    }
}
