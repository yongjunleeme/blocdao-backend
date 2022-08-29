package com.blocdao.project.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberStack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_stack_id")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stack stack;
}
