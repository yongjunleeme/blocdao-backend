package com.blocdao.project.dto.member.response;

import com.blocdao.project.entity.Member;
import com.blocdao.project.entity.MemberStacks;
import com.blocdao.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String nickName;
    private String imageUrl;
    private String email;
    private String phone;
    private String profileLink;
    private Boolean isWithdrawal;
    private LocalDate dataWithdrawal;
    private List<MemberStacks> memberStacks;
    private List<Project> projects;

    public MemberResponseDto(Member member) {
        this.nickName = member.getNickName();
        this.imageUrl = member.getImageUrl();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.profileLink = member.getProfileLink();
        this.isWithdrawal = member.getIsWithdrawal();
        this.dataWithdrawal = member.getDataWithdrawal();
        this.memberStacks = member.getMemberStacks();
        this.projects = member.getProjects();
    }
}
