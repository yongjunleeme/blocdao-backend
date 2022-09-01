package com.blocdao.project.domain.stack;

import com.blocdao.project.domain.stack.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {
}
