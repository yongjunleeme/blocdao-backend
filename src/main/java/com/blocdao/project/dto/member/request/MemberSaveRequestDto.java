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
public class MemberSaveRequestDto {

    private String nickName;

    private String imageUrl;

    private String email;

    private String phone;

    private String profileLink;

    private List<Long> Stacks = new ArrayList<>();

}
