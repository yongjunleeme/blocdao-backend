package com.blocdao.project.repository;

import com.blocdao.project.entity.Stack;
import com.blocdao.project.repository.custom.StackCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long>, StackCustomRepository {
    Optional<Stack> findByName(String stack);
}
