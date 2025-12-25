package goonerd.devhub.adapters.out.submission;

import goonerd.devhub.adapters.out.submission.entity.SubmissionEntity;
import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.domain.common.AuditInfo;

public class SubmissionMapper {

    public static SubmissionEntity toEntity(Submission submission) {
        return SubmissionEntity.builder()
                .submissionGuid(submission.getSubmissionGuid())
                .projectGuid(submission.getProjectGuid())
                .submitterId(submission.getSubmitterId())
                .submitterName(submission.getSubmitterName())
                .motivation(submission.getMotivation())
                .position(submission.getPosition())
                .skillLevel(submission.getSkillLevel())
                .submissionStatus(submission.getSubmissionStatus())
                .submittedAt(submission.getSubmittedAt())
                .build();
    }

    public static Submission toDomain(SubmissionEntity submissionEntity) {
        return Submission.reconstruct(
                submissionEntity.getSubmissionGuid(),
                submissionEntity.getProjectGuid(),
                submissionEntity.getSubmitterId(),
                submissionEntity.getSubmitterName(),
                submissionEntity.getMotivation(),
                submissionEntity.getPosition(),
                submissionEntity.getSkillLevel(),
                submissionEntity.getSubmissionStatus(),
                submissionEntity.getSubmittedAt(),
                toAuditInfo(submissionEntity)
        );
    }

    private static AuditInfo toAuditInfo(SubmissionEntity submissionEntity) {
        return AuditInfo.of(submissionEntity.getCreatedBy(),
                submissionEntity.getCreatedAt(),
                submissionEntity.getModifiedBy(),
                submissionEntity.getModifiedAt()
        );
    }
}