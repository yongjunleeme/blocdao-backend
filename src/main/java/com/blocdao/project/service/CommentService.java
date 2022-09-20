package com.blocdao.project.service;

import com.blocdao.project.dto.page.PageRequestDto;
import com.blocdao.project.dto.page.PageResultDto;
import com.blocdao.project.dto.comment.response.CommentGetCommentResponseDto;
import com.blocdao.project.entity.Comment;

public interface CommentService {

     PageResultDto<CommentGetCommentResponseDto, Comment> getComments(PageRequestDto requestDto);

    default CommentGetCommentResponseDto entityToDto(Comment comment) {
        CommentGetCommentResponseDto dto = CommentGetCommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .nickname(comment.getMember().getNickName())
                .createTime(comment.getCreateTime()).build();

        return dto;
    }
}
