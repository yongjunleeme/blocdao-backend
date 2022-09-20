package com.blocdao.project.controller;

import com.blocdao.project.dto.page.PageRequestDto;
import com.blocdao.project.dto.page.PageResultDto;
import com.blocdao.project.dto.comment.request.CommentCreateRequestDto;
import com.blocdao.project.dto.comment.response.CommentGetCommentResponseDto;
import com.blocdao.project.entity.Comment;
import com.blocdao.project.entity.Member;
import com.blocdao.project.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;

    @PostMapping()
    public Long createComment(@RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        return commentService.createComment(commentCreateRequestDto, member);
    }

    @GetMapping()
    public PageResultDto<CommentGetCommentResponseDto, Comment> getComments() {
        PageRequestDto requestDto = new PageRequestDto();
        return commentService.getComments(requestDto);
    }
}
