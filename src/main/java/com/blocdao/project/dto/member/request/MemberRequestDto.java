package com.blocdao.project.dto.member.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    private String imageUrl;

    @Email(message = "이메일 형식만 입력이 가능합니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    private String phone;

    private String profileLink;

    private List<Long> Stacks = new ArrayList<>();

}
