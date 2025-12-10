package goonerd.devhub.project.dto;

import goonerd.devhub.project.entity.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "프로젝트 응답 정보")
public class ProjectResponseDto {

    @Schema(description = "프로젝트 GUID")
    private String projectGuid;
    @Schema(description = "사용자 ID")
    private String userId;
    @Schema(description = "사용자 이름")
    private String username;
    @Schema(description = "프로젝트 제목")
    private String title;
    @Schema(description = "프로젝트 설명")
    private String description;
    @Schema(description = "모집 인원")
    private int recruitCount;
    @Schema(description = "시작일")
    private LocalDate startDate;
    @Schema(description = "종료일")
    private LocalDate endDate;
    @Schema(description = "생성일")
    private LocalDateTime createdAt;
    @Schema(description = "수정일")
    private LocalDateTime modifiedAt;
    @Schema(description = "생성자")
    private String createdBy;
    @Schema(description = "수정자")
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