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
public class ApplicationResponseDto {

    private String applicationGuid;
    private String projectGuid;
    private String applicantGuid;
    private String applicantEmail;
    private String applicantName;
    private String positionName;
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

    public static ApplicationResponseDto fromDomain(Application application) {
        return ApplicationResponseDto.builder()
                .applicationGuid(application.getApplicationGuid())
                .projectGuid(application.getProjectGuid())
                .applicantGuid(application.getApplicantGuid())
                .applicantEmail(application.getApplicantEmail())
                .applicantName(application.getApplicantName())
                .positionName(application.getPositionName())
                .proficiency(application.getSkillLevel())
                .status(application.getApplicationStatus().name())
                .appliedAt(application.getSubmittedAt())
                .createdAt(application.getAuditInfo().getCreatedAt())
                .modifiedAt(application.getAuditInfo().getModifiedAt())
                .createdBy(application.getAuditInfo().getCreatedBy())
                .modifiedBy(application.getAuditInfo().getModifiedBy())
                .build();
    }
}