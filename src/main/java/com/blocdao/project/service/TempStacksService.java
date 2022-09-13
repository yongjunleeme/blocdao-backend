package com.blocdao.project.service;

import com.blocdao.project.entity.ProjectStacks;
import com.blocdao.project.entity.Stacks;
import com.blocdao.project.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempStacksService {

    private final StackRepository stackRepository;

    public Stacks findStacks(ProjectStacks projectStacks){
        Stacks stacks = stackRepository.findByEnumStacks(projectStacks.getStacks()
                .getEnumStacks());
        return stacks;
    }
}
