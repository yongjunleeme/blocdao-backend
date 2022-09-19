package com.blocdao.project.dto.comment.request;

import lombok.Data;

@Data
public class CommentCreateRequestDto {
    private Long projectId;
    private String content;
}
