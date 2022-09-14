package com.blocdao.project.dto.stack.request;

import com.blocdao.project.entity.enums.EnumStacks;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class StackRequestDto {

    @NotBlank(message = "기술스택은 입력이 필수 입니다.")
    @Enumerated(EnumType.STRING)
    private EnumStacks enumStacks;

    private String image;
}
