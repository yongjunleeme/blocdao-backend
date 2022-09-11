package com.blocdao.project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberStacks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_stack_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stack_id")
    private Stacks stacks;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberStacks().remove(this);
        }
        this.member = member;
        member.getMemberStacks().add(this);
    }

    public void setStacks(Stacks stacks) {
        if (this.stacks != null) {
            this.stacks.getMemberStacks().remove(this);
        }
        this.stacks = stacks;
        stacks.getMemberStacks().add(this);
    }
}
