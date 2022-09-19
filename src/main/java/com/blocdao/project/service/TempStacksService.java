package com.blocdao.project.service;

import com.blocdao.project.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempStacksService {

    private final StackRepository stacksRepository;
}
