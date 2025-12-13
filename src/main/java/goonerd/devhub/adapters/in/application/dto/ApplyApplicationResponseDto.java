package goonerd.devhub.adapters.in.application.dto;

import goonerd.devhub.domain.application.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ApplyApplicationResponseDto {

    private String applicationGuid;
    private String projectGuid;
    private String userId;
    private String username;
    private String position;
    private String proficiency;
    private String status;
    private LocalDateTime appliedAt;

    @Schema(description = "생성일")
    private LocalDateTime createdAt;

    @Schema(description = "수정일")
    private LocalDateTime modifiedAt;

    @Schema(description = "생성자")
    private String createdBy;

    @Schema(description = "수정자")
    private String modifiedBy;

    public static ApplyApplicationResponseDto fromDomain(Application application) {
        return ApplyApplicationResponseDto.builder()
                .applicationGuid(application.getApplicationGuid())
                .projectGuid(application.getProjectGuid())
                .userId(application.getUserId())
                .username(application.getUsername())
                .position(application.getPositionRequirement().getPosition())
                .proficiency(application.getPositionRequirement().getSkillLevel().name())
                .status(application.getStatus().name())
                .appliedAt(application.getAppliedAt())
                .createdAt(application.getAuditInfo().getCreatedAt())
                .modifiedAt(application.getAuditInfo().getModifiedAt())
                .createdBy(application.getAuditInfo().getCreatedBy())
                .modifiedBy(application.getAuditInfo().getModifiedBy())
                .build();
    }
}