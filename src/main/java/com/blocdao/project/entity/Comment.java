package com.blocdao.project.entity;

import com.blocdao.project.dto.comment.request.CommentUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;

    private String content;

    public Comment update(Comment comment, CommentUpdateRequestDto commentUpdateRequestDto) {
        preUpdate();
        this.id = comment.getId();
        this.project = comment.getProject();
        this.member = comment.getMember();
        this.content = commentUpdateRequestDto.getContent();
        return this;
    }
}
