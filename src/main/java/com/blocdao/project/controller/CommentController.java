package com.blocdao.project.controller;

import com.blocdao.project.dto.comment.request.CommentRequestDto;
import com.blocdao.project.dto.comment.response.CommentListResponseDto;
import com.blocdao.project.dto.comment.response.CommentResponseDto;
import com.blocdao.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


/*    @GetMapping("/user/exists/{email}")
    public ResponseEntity<Void> checkDuplicateEmail(@PathVariable java.lang.String email){
        userService.verifyEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @PostMapping("/api/project/{projectId}/comments")
    public ResponseEntity<CommentResponseDto> save(@RequestBody CommentRequestDto commentRequestDto,
                                                   @PathVariable("projectId") Long projectId){
        CommentResponseDto response = commentService.saveComment(commentRequestDto, projectId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/api/projects/{projectId}/comments")
    public ResponseEntity<List<CommentListResponseDto>> getCommentList(@PathVariable Long projectId) {
        List<CommentListResponseDto> commentListResponseDto = commentService.getCommentList(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(commentListResponseDto);
    }
}
