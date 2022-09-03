package com.blocdao.project.controller;

import com.blocdao.project.dto.comment.request.CommentRequestDto;
import com.blocdao.project.dto.comment.response.CommentResponseDto;
import com.blocdao.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
