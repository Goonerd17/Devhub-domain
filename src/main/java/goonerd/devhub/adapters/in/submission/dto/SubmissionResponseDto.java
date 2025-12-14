package goonerd.devhub.adapters.in.submission.dto;

import goonerd.devhub.domain.submission.Submission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SubmissionResponseDto {

    private String submissionGuid;
    private String projectGuid;
    private String submitterId;
    private String submitterName;
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

    public static SubmissionResponseDto fromDomain(Submission submission) {
        return SubmissionResponseDto.builder()
                .submissionGuid(submission.getSubmissionGuid())
                .projectGuid(submission.getProjectGuid())
                .submitterId(submission.getSubmitterId())
                .submitterName(submission.getSubmitterName())
                .position(submission.getPositionRequirement().getPosition())
                .proficiency(submission.getPositionRequirement().getSkillLevel().name())
                .status(submission.getSubmissionStatus().name())
                .appliedAt(submission.getSubmittedAt())
                .createdAt(submission.getAuditInfo().getCreatedAt())
                .modifiedAt(submission.getAuditInfo().getModifiedAt())
                .createdBy(submission.getAuditInfo().getCreatedBy())
                .modifiedBy(submission.getAuditInfo().getModifiedBy())
                .build();
    }
}