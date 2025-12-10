package goonerd.devhub.project.dto;

import goonerd.devhub.project.entity.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProjectResponseDto {

    private String projectGuid;
    private String userId;
    private String username;
    private String title;
    private String description;
    private int recruitCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;

    public static ProjectResponseDto fromEntity(Project project) {
        return ProjectResponseDto.builder()
                .projectGuid(project.getProjectGuid())
                .userId(project.getUserId())
                .username(project.getUsername())
                .title(project.getTitle())
                .description(project.getDescription())
                .recruitCount(project.getRecruitCount())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createdAt(project.getCreatedAt())
                .modifiedAt(project.getModifiedAt())
                .createdBy(project.getCreatedBy())
                .modifiedBy(project.getModifiedBy())
                .build();
    }
}