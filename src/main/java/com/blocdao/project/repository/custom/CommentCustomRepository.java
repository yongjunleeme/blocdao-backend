package com.blocdao.project.repository.custom;

import com.blocdao.project.entity.Comment;

import java.util.List;

public interface CommentCustomRepository {

    List<Comment> findAllByProjectId(Long projectId);
}
