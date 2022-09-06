package com.blocdao.project.repository;

import com.blocdao.project.entity.Member;
import com.blocdao.project.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {
}