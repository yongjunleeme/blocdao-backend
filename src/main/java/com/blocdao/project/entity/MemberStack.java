package com.blocdao.project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberStack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberStack_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_uid")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stack_id")
    private Stack stack;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberStacks().remove(this);
        }
        this.member = member;
        member.getMemberStacks().add(this);
    }

    public void setStack(Stack stack) {
        if (this.stack != null) {
            this.stack.getMemberStack().remove(this);
        }
        this.stack = stack;
        stack.getMemberStack().add(this);
    }
}
