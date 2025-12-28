package goonerd.devhub.adapters.in.project.dto;

import goonerd.devhub.applications.project.ProjectWithStatus;
import goonerd.devhub.domains.project.Project;
import goonerd.devhub.domains.project.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Schema(description = "프로젝트 응답 정보")
public class ProjectResponseDto {

    @Schema(description = "프로젝트 GUID")
    private String projectGuid;

    @Schema(description = "사용자 ID")
    private String authorGuid;

    @Schema(description = "사용자 이름")
    private String authorName;

    @Schema(description = "프로젝트 제목")
    private String title;

    @Schema(description = "프로젝트 설명")
    private String content;

    @Schema(description = "프로젝트 상태")
    private String projectStatus;

    @Schema(description = "모집 인원")
    private int recruitCount;

    @Schema(description = "모집 포지션 목록")
    private List<PositionResponseDto> positionResponseDtoList;

    private List<String> skillList;

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

    public static ProjectResponseDto fromDomainReadModel(ProjectWithStatus projectWithStatus) {
        return ProjectResponseDto.builder()
                .projectGuid(projectWithStatus.getProject().getProjectGuid())
                .authorGuid(projectWithStatus.getProject().getAuthorGuid())
                .authorName(projectWithStatus.getProject().getAuthorName())
                .title(projectWithStatus.getProject().getTitle())
                .content(projectWithStatus.getProject().getContent())
                .projectStatus(projectWithStatus.getProjectStatus().toString())
                .recruitCount(projectWithStatus.getProject().getRecruitCount())
                .positionResponseDtoList(
                        projectWithStatus.getProject().getPositionList().stream()
                                .map(PositionResponseDto::fromDomain)
                                .toList())
                .skillList(projectWithStatus.getProject().getSkillList())
                .startDate(projectWithStatus.getProject().getStartDate())
                .endDate(projectWithStatus.getProject().getEndDate())
                .createdAt(projectWithStatus.getProject().getAuditInfo().getCreatedAt())
                .modifiedAt(projectWithStatus.getProject().getAuditInfo().getModifiedAt())
                .createdBy(projectWithStatus.getProject().getAuditInfo().getCreatedBy())
                .modifiedBy(projectWithStatus.getProject().getAuditInfo().getModifiedBy())
                .build();
    }

    public static ProjectResponseDto fromDomain(Project project) {
        return ProjectResponseDto.builder()
                .projectGuid(project.getProjectGuid())
                .authorGuid(project.getAuthorGuid())
                .authorName(project.getAuthorName())
                .title(project.getTitle())
                .content(project.getContent())
                .projectStatus(ProjectStatus.RECRUITING.name())
                .recruitCount(project.getRecruitCount())
                .positionResponseDtoList(
                        project.getPositionList().stream()
                                .map(PositionResponseDto::fromDomain)
                                .toList())
                .skillList(project.getSkillList())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createdAt(project.getAuditInfo().getCreatedAt())
                .modifiedAt(project.getAuditInfo().getModifiedAt())
                .createdBy(project.getAuditInfo().getCreatedBy())
                .modifiedBy(project.getAuditInfo().getModifiedBy())
                .build();
    }
}