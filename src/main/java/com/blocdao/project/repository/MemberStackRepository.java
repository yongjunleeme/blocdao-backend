package com.blocdao.project.repository;

import com.blocdao.project.entity.MemberStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberStackRepository extends JpaRepository<MemberStack, Long> {
}
