package com.blocdao.project.dto.comment.request;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;

    private String uid;

    private String content;
}
