package com.blocdao.project.dto.member.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupRequestDto {

    private String nickName;

    private String imageUrl;

    private String email;

    private String phone;

    private String profileLink;


}
