package com.blocdao.project.repository;

import com.blocdao.project.entity.Stacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<Stacks, Long> {
}
