package com.blocdao.project.dto.member.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberSignupResponseDto {
    private String uid;

    private String nickName;

    private String imageUrl;

    private String email;

    private String phone;

    private String profileLink;

    private Boolean isWithdrawal = false;

    private String token;
}
