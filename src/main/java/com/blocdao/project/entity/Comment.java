package com.blocdao.project.entity;


import com.blocdao.project.dto.comment.request.CommentCreateRequestDto;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_uid")
    private Member member;

    @Column(nullable = false)
    private String content;

    public Comment(CommentCreateRequestDto commentCreateRequestDto, Member member) {
        this.member = member;
        this.content = commentCreateRequestDto.getContent();
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getComments().remove(this);
        }
        this.member = member;
        member.getComments().add(this);
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.getComments().remove(this);
        }
        this.project = project;
        project.getComments().add(this);
    }
}
