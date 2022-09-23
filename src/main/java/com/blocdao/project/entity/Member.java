package com.blocdao.project.entity;

import com.blocdao.project.dto.member.request.MemberSingupRequestDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @Column(name = "member_uid")
    private String uid;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "nickName 값을 입력하세요.")
    private String nickName;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @NotBlank(message = "email 값을 입력하세요.")
    @Email
    private String email;

    private String phone;

    @Column
    private String profileLink;

    //탈퇴 여부
    @Column
    private final Boolean isWithdrawal = false;

    //탈퇴 날짜
    @Column
    private LocalDate dataWithdrawal = null;

    @OneToMany(mappedBy = "member")
    private final List<MemberStack> memberStacks = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Comment> comments = new ArrayList<>();

    public Member(MemberSingupRequestDto memberSingupRequestDto, String uid) {
        this.uid = uid;
        this.nickName = memberSingupRequestDto.getNickName();
        this.imageUrl = memberSingupRequestDto.getImageUrl();
        this.email = memberSingupRequestDto.getEmail();
        this.phone = memberSingupRequestDto.getPhone();
        this.profileLink = memberSingupRequestDto.getProfileLink();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
