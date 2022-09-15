package com.blocdao.project.controller;

import com.blocdao.project.dto.stack.request.StacksCreateRequestDto;
import com.blocdao.project.dto.stack.response.StacksCreateResponseDto;
import com.blocdao.project.dto.stack.response.StacksFindStacksResponseDto;
import com.blocdao.project.service.StackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stack")
public class StackController {

    private final StackService stackService;

    //기술스택 등록
    @PostMapping()
    public ResponseEntity<StacksCreateResponseDto> create(@RequestBody @Valid StacksCreateRequestDto stacksCreateRequestDto) {
        return stackService.create(stacksCreateRequestDto);
    }

    //기술스택 찾기
    @GetMapping()
    public List<StacksFindStacksResponseDto> findStacks() {
        return stackService.findStacks();
    }
}
