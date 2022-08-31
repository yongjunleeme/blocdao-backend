package com.blocdao.project.domain.memberStack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberStackRepository extends JpaRepository<MemberStack, Long> {
}
