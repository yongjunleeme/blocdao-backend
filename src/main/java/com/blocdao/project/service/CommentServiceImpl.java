package com.blocdao.project.service;

import com.blocdao.project.dto.page.PageRequestDto;
import com.blocdao.project.dto.page.PageResultDto;
import com.blocdao.project.dto.comment.request.CommentCreateRequestDto;

import com.blocdao.project.dto.comment.response.CommentGetCommentResponseDto;
import com.blocdao.project.entity.Comment;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.Project;
import com.blocdao.project.exception.CustomException;
import com.blocdao.project.exception.ErrorCode;
import com.blocdao.project.repository.CommentRepository;
import com.blocdao.project.repository.MemberRepository;
import com.blocdao.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public Long createComment(CommentCreateRequestDto commentCreateRequestDto, Member principal) {

        Comment comment = new Comment(commentCreateRequestDto);

        Member member = memberRepository.findById(principal.getUid())
                .orElseThrow(()->{
                    throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
                });

        comment.setMember(member);

        Project project = projectRepository.findById(commentCreateRequestDto.getProjectId())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");
                });

        comment.setProject(project);

        commentRepository.save(comment);

        return comment.getId();

    }

    @Override
    public PageResultDto<CommentGetCommentResponseDto, Comment> getComments(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("id").descending());

        Page<Comment> result = commentRepository.findAll(pageable);

        Function<Comment, CommentGetCommentResponseDto> fn = (entity -> entityToDto(entity));

        return new PageResultDto<>(result, fn);
    }


}
