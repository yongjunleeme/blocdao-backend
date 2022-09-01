package com.blocdao.project.entity;

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

    @Column
    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column
    private String profileLink;

    //탈퇴 여부
    @Column(nullable = false)
    private Boolean isWithdrawal = false;

    //탈퇴 날짜
    @Column
    private LocalDate dataWithdrawal;

}
