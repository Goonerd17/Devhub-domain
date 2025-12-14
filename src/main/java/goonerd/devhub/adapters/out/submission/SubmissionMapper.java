package goonerd.devhub.adapters.out.submission;

import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.domain.submission.PositionRequirement;
import goonerd.devhub.domain.common.AuditInfo;

public class SubmissionMapper {

    public static SubmissionEntity toEntity(Submission domain) {
        PositionRequirement req = domain.getPositionRequirement();

        PositionRequirementEmbeddable embeddable =
                new PositionRequirementEmbeddable(
                        req.getPosition(),
                        req.getSkillLevel()
                );
        return SubmissionEntity.builder()
                .submissionGuid(domain.getSubmissionGuid())
                .projectGuid(domain.getProjectGuid())
                .submitterId(domain.getSubmitterId())
                .submitterName(domain.getSubmitterName())
                .motivation(domain.getMotivation())
                .requirement(embeddable)
                .status(domain.getStatus())
                .submittedAt(domain.getSubmittedAt())
                .build();
    }

    public static Submission toDomain(SubmissionEntity submissionEntity) {
        PositionRequirementEmbeddable emb = submissionEntity.getRequirement();
        PositionRequirement requirement =
                PositionRequirement.of(
                        emb.getPosition(),
                        emb.getSkillLevel()
                );

        return Submission.reconstruct(
                submissionEntity.getSubmissionGuid(),
                submissionEntity.getProjectGuid(),
                submissionEntity.getSubmitterId(),
                submissionEntity.getSubmitterName(),
                submissionEntity.getMotivation(),
                requirement,
                submissionEntity.getStatus(),
                submissionEntity.getSubmittedAt(),
                toAuditInfo(submissionEntity)
        );
    }

    private static AuditInfo toAuditInfo(SubmissionEntity submissionEntity) {
        return AuditInfo.of(
                submissionEntity.getCreatedBy(),
                submissionEntity.getCreatedAt(),
                submissionEntity.getModifiedBy(),
                submissionEntity.getModifiedAt()
        );
    }
}