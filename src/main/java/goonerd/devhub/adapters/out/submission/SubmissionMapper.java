package goonerd.devhub.adapters.out.submission;

import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.domain.submission.PositionRequirement;
import goonerd.devhub.domain.common.AuditInfo;

public class SubmissionMapper {

    public static SubmissionEntity toEntity(Submission submission) {
        PositionRequirement positionRequirement = submission.getPositionRequirement();
        PositionRequirementEmbeddable positionRequirementEmbeddable = PositionRequirementEmbeddable.builder()
                .position(positionRequirement.getPosition())
                .skillLevel(positionRequirement.getSkillLevel())
                .build();

        return SubmissionEntity.builder()
                .submissionGuid(submission.getSubmissionGuid())
                .projectGuid(submission.getProjectGuid())
                .submitterId(submission.getSubmitterId())
                .submitterName(submission.getSubmitterName())
                .motivation(submission.getMotivation())
                .requirement(positionRequirementEmbeddable)
                .submissionStatus(submission.getSubmissionStatus())
                .submittedAt(submission.getSubmittedAt())
                .build();
    }

    public static Submission toDomain(SubmissionEntity submissionEntity) {
        PositionRequirementEmbeddable positionRequirementEmbeddable = submissionEntity.getRequirement();
        PositionRequirement positionRequirement = PositionRequirement.of(
                positionRequirementEmbeddable.getPosition(),
                positionRequirementEmbeddable.getSkillLevel()
        );

        return Submission.reconstruct(
                submissionEntity.getSubmissionGuid(),
                submissionEntity.getProjectGuid(),
                submissionEntity.getSubmitterId(),
                submissionEntity.getSubmitterName(),
                submissionEntity.getMotivation(),
                positionRequirement,
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