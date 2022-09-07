package com.blocdao.project.dto.comment.response;

import com.blocdao.project.entity.Member;
import lombok.Getter;

@Getter
public class CommentListResponseDto {
    private String uid;
    private String nickName;
    private String imageUrl;
    private String content;

    public CommentListResponseDto(Member member, String content) {
        this.uid = member.getUid();
        this.nickName = member.getNickName();
        this.imageUrl = member.getImageUrl();
        this.content = content;
    }
}
