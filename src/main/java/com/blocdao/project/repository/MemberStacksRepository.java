package com.blocdao.project.repository;

import com.blocdao.project.entity.MemberStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberStacksRepository extends JpaRepository<MemberStacks, String> {
}
