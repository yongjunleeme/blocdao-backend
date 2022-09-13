package com.blocdao.project.controller;

import com.blocdao.project.dto.comment.request.CommentDeleteRequestDto;
import com.blocdao.project.dto.comment.request.CommentRequestDto;
import com.blocdao.project.dto.comment.request.CommentUpdateRequestDto;
import com.blocdao.project.dto.comment.response.CommentListResponseDto;
import com.blocdao.project.dto.comment.response.CommentResponseDto;
import com.blocdao.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/projects")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

/*    @GetMapping("/user/exists/{email}")
    public ResponseEntity<Void> checkDuplicateEmail(@PathVariable java.lang.String email){
        userService.verifyEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @PostMapping("/{projectId}/comments")
    public ResponseEntity<CommentResponseDto> save(@RequestBody CommentRequestDto commentRequestDto,
                                                   @PathVariable("projectId") Long projectId){
        CommentResponseDto response = commentService.saveComment(commentRequestDto, projectId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/comments")
    public ResponseEntity<CommentListResponseDto> getCommentList(@PathVariable Long projectId) {
        CommentListResponseDto commentListResponseDto = commentService.getCommentList(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(commentListResponseDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{projectId}/comments/{commentId}")
    public ResponseEntity updateComment(@RequestBody CommentUpdateRequestDto commentUpdateRequestDto,
                                        @PathVariable("commentId") Long commentId) {
        commentService.updateComment(commentUpdateRequestDto, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{projectId}/comments/{commentId}")
    public ResponseEntity deleteComment(@RequestBody CommentDeleteRequestDto commentDeleteRequestDto,
                                        @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentDeleteRequestDto, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
