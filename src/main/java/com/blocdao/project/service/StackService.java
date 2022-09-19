package com.blocdao.project.service;

import com.blocdao.project.dto.stack.request.StackCreateRequestDto;
import com.blocdao.project.dto.stack.response.StackCreateResponseDto;
import com.blocdao.project.dto.stack.response.StackFindStacksResponseDto;
import com.blocdao.project.entity.Stack;
import com.blocdao.project.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StackService {
    private final StackRepository stackRepository;

    public ResponseEntity<StackCreateResponseDto> create(StackCreateRequestDto stackCreateRequestDto) {

        Stack stack = new Stack(stackCreateRequestDto);
        stackRepository.save(stack);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new StackCreateResponseDto(stack));
    }

    public List<StackFindStacksResponseDto> findStacks() {

        List<StackFindStacksResponseDto> stacksFindStacksResponseDtos = new ArrayList<>();

        List<Stack> stacks = stackRepository.findAll();

        stacks.forEach(
                (stack)->{
                    stacksFindStacksResponseDtos.add(new StackFindStacksResponseDto(stack));
                });

        return stacksFindStacksResponseDtos;
    }
}
