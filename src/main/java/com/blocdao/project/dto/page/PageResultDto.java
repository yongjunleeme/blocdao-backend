package com.blocdao.project.dto.page;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDto<dto, en> {
    private List<dto> dtoList;

    public PageResultDto(Page<en> result, Function<en, dto> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
