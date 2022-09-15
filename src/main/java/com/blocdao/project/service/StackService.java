package com.blocdao.project.service;

import com.blocdao.project.dto.stack.request.StacksCreateRequestDto;
import com.blocdao.project.dto.stack.response.StacksCreateResponseDto;
import com.blocdao.project.dto.stack.response.StacksFindStacksResponseDto;
import com.blocdao.project.entity.Stacks;
import com.blocdao.project.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StackService {
    private final StackRepository stackRepository;

    public ResponseEntity<StacksCreateResponseDto> create(StacksCreateRequestDto stacksCreateRequestDto) {

        Stacks stacks = new Stacks(stacksCreateRequestDto);
        stackRepository.save(stacks);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new StacksCreateResponseDto(stacks));
    }

    public List<StacksFindStacksResponseDto> findStacks() {

        List<StacksFindStacksResponseDto> stacksFindStacksResponseDtos = new ArrayList<>();

        List<Stacks> stacks = stackRepository.findAll();

        stacks.forEach(
                (stack)->{
                    stacksFindStacksResponseDtos.add(new StacksFindStacksResponseDto(stack));
                });

        return stacksFindStacksResponseDtos;
    }
}
