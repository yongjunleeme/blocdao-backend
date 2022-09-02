package com.blocdao.project.dto.member.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSignupRequestDto {
    private String uid;

    private String nickName;

    private String imageUrl;

    private String email;

    private String phone;

    private String profileLink;

}
