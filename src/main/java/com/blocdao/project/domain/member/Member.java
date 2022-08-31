package com.blocdao.project.domain.member;

import com.blocdao.project.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    private String uid;

    @Column(nullable = false, length = 20)
    private String nickName;

    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    private String profileLink;

    @Column(nullable = false)
    private Boolean isWithdrawal = false;

    private LocalDate dataWithdrawal;
}
