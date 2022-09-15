package com.blocdao.project.dto.member.request;

import lombok.*;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSingupRequestDto {

    private String nickName;

    private String imageUrl;

    @Email
    private String email;

    private String phone;

    private String profileLink;

    private List<String> stacks;

}
