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

    @Pattern(regexp = "^\\\\d{3}\\\\d{3,4}\\\\d{4}$",message = "숫자로 11자리 만 입력 가능합니다.")
    private String phone;

    private String profileLink;

    private List<Long> memberStacks = new ArrayList<>();

}
