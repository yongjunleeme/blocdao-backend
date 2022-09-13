package com.blocdao.project.repository.custom;

import com.blocdao.project.entity.Stacks;
import com.blocdao.project.entity.enums.EnumStacks;

public interface StackCustomRepository {
    Stacks findByEnumStacks(EnumStacks enumStacks);
}
