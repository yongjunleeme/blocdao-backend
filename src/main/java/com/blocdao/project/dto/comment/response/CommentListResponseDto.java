package com.blocdao.project.dto.comment.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponseDto {
    private List<CommentsResponseDto> comments;

    public CommentListResponseDto(List<CommentsResponseDto> comments) {
        this.comments = comments;
    }
}
