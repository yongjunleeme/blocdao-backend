package com.blocdao.project.dto.comment.response;

import com.blocdao.project.entity.Comment;
import com.blocdao.project.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {
    private String uid;
    private String nickName;
    private String imageUrl;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public CommentsResponseDto(Member member, Comment comment) {
        this.uid = member.getUid();
        this.nickName = member.getNickName();
        this.imageUrl = member.getImageUrl();
        this.content = comment.getContent();
        this.createTime = comment.getCreateTime();
        this.updateTime = comment.getUpdateTime();
    }
}
