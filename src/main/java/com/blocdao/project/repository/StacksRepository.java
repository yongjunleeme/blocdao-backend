package com.blocdao.project.repository;

import com.blocdao.project.entity.Stacks;
import com.blocdao.project.repository.custom.StackCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StacksRepository extends JpaRepository<Stacks, Long>, StackCustomRepository {
    Optional<Stacks> findByName(String stack);
}
