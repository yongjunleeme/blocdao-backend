package com.blocdao.project.service;

import com.blocdao.project.dto.comment.request.CommentRequestDto;
import com.blocdao.project.dto.comment.response.CommentListResponseDto;
import com.blocdao.project.dto.comment.response.CommentResponseDto;
import com.blocdao.project.dto.comment.response.CommentsResponseDto;
import com.blocdao.project.entity.Comment;
import com.blocdao.project.entity.Member;
import com.blocdao.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public CommentListResponseDto getCommentList(Long projectId) {
        List<Comment> comments = commentRepository.findAllByProjectId(projectId);
        List<CommentsResponseDto> commentsResponseDtos = new ArrayList<>();

        for (Comment comment : comments) {
            Member member = comment.getMember();
            CommentsResponseDto commentsResponseDto = new CommentsResponseDto(member, comment);
            commentsResponseDtos.add(commentsResponseDto);
        }

        return new CommentListResponseDto(commentsResponseDtos);
    }
}
