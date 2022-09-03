package com.blocdao.project.service;

import com.blocdao.project.dto.comment.request.CommentRequestDto;
import com.blocdao.project.dto.comment.response.CommentResponseDto;
import com.blocdao.project.entity.Comment;
import com.blocdao.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProjectService projectService;

    public CommentResponseDto saveComment(CommentRequestDto commentRequestDto, Long projectId) {

        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .project(projectService.getProjectById(projectId))
                .build();

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .id(saveComment.getId())
                .content(saveComment.getContent())
                .build();

        return commentResponseDto;
    }
}
