package com.blocdao.project.service;

import com.blocdao.project.repository.StacksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempStacksService {

    private final StacksRepository stacksRepository;
}
