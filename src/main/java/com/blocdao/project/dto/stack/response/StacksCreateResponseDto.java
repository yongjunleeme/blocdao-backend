package com.blocdao.project.dto.stack.response;

import com.blocdao.project.entity.Stacks;

public class StacksCreateResponseDto {
    private Long id;

    public StacksCreateResponseDto(Stacks stacks) {
        this.id = stacks.getId();
    }
}
