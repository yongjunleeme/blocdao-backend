package com.blocdao.project.service;

import com.blocdao.project.dto.comment.request.CommentCreateRequestDto;

import com.blocdao.project.entity.Comment;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.repository.CommentRepository;
import com.blocdao.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;

    public Long createComment(CommentCreateRequestDto commentCreateRequestDto, Member member) {

        Comment comment = new Comment(commentCreateRequestDto, member);

        Project project = projectRepository.findById(commentCreateRequestDto.getProjectId())
                .orElseThrow(()->{
                    throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");
                });

        comment.setProject(project);

        commentRepository.save(comment);

        return comment.getId();

    }
}
