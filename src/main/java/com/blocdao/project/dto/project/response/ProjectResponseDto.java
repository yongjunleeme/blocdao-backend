package com.blocdao.project.dto.project.response;

import com.blocdao.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProjectResponseDto {
    private Long projectId;

    public ProjectResponseDto(Project project) {
        this.projectId = project.getId();
    }
}
