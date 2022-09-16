package com.blocdao.project.dto.member.response;

import com.blocdao.project.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileResponseDto {
    private String nickName;
    private String imageUrl;
    private String email;
    private String phone;
    private String profileLink;
    private List<String> projectTitle = new ArrayList<>();
    private List<String> startTime = new ArrayList<>();
    private List<String> endTime = new ArrayList<>();

    public MemberProfileResponseDto(Member member) {
        this.nickName = member.getNickName();
        this.imageUrl = member.getImageUrl();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.profileLink = member.getProfileLink();

        member.getProjects().forEach((project -> {
            this.projectTitle.add(project.getTitle());
            this.startTime.add(project.getStartTime());
            this.endTime.add(project.getEndTime());
        }));

    }
}
