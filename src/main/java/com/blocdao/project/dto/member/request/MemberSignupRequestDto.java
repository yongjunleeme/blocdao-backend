package com.blocdao.project.dto.member.request;

import com.blocdao.project.controller.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    private String imageUrl;

    @Email(message = "이메일 형식만 입력이 가능합니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    private String phone;

    private String profileLink;

    private List<Long> memberStacks = new ArrayList<>();

}
