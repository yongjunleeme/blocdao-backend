package com.blocdao.project.entity;

import com.blocdao.project.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void creationWithBuilder() {
        Member member = Member.builder()
                .uid("1")
                .nick_name("nick_name")
                .image_url("www.image.com")
                .email("mail@mail.com")
                .phone("01000000000")
                .profile_link("www.profile.com")
                .is_withdrawal(false)
                .build();

        assertThat(member.getUid()).isEqualTo("1");
        assertThat(member.getNick_name()).isEqualTo("nick_name");
    }
}
