package goonerd.devhub.project.dto;

import goonerd.devhub.project.entity.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProjectResponseDto {

    private Long id;
    private String username;
    private String description;
    private int recruitCount;
    private int viewCount;
    private LocalDate startDate;
    private LocalDate endDate;

    public static ProjectResponseDto fromEntity(Project project) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .username(project.getUsername())
                .description(project.getDescription())
                .recruitCount(project.getRecruitCount())
                .viewCount(project.getViewCount())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .build();
    }


}