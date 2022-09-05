package com.blocdao.project.dto.member.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberFindMyResponseDto {
    private String nickName;
    private String imageUrl;
    private String email;
    private String phone;
    private String profileLink;
}
