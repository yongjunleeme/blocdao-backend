package com.blocdao.project.dto.comment.response;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import lombok.Builder;

@Builder
public class CommentResponseDto {

    private Long id;

    private String uid;

    private String content;
}
