package com.blocdao.project.controller;

import com.blocdao.project.dto.stack.request.StackCreateRequestDto;
import com.blocdao.project.dto.stack.response.StackCreateResponseDto;
import com.blocdao.project.dto.stack.response.StackFindStacksResponseDto;
import com.blocdao.project.service.StackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stacks")
public class StackController {

    private final StackService stackService;

    //기술스택 등록
    @PostMapping()
    public ResponseEntity<StackCreateResponseDto> create(@RequestBody @Valid StackCreateRequestDto stacksCreateRequestDto) {
        return stackService.create(stacksCreateRequestDto);
    }

    //기술스택 찾기
    @GetMapping()
    public List<StackFindStacksResponseDto> findStacks() {
        return stackService.findStacks();
    }
}
