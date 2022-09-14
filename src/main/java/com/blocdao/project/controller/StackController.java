package com.blocdao.project.controller;

import com.blocdao.project.dto.stack.request.StackRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stack")
public class StackController {
    @PostMapping()
    public Long create(@RequestBody @Valid StackRequestDto stackRequestDto) {
        return 1L;
    }
}
