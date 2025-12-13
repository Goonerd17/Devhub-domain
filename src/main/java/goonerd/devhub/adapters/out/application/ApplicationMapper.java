package goonerd.devhub.adapters.out.application;

import goonerd.devhub.domain.application.Application;
import goonerd.devhub.domain.application.PositionRequirement;
import goonerd.devhub.domain.common.AuditInfo;

public class ApplicationMapper {

    // Domain → Entity
    public static ApplicationEntity toEntity(Application domain) {
        PositionRequirement req = domain.getPositionRequirement();

        PositionRequirementEmbeddable embeddable =
                new PositionRequirementEmbeddable(
                        req.getPosition(),
                        req.getSkillLevel()
                );
        return ApplicationEntity.builder()
                .applicationGuid(domain.getApplicationGuid())
                .projectGuid(domain.getProjectGuid())
                .userId(domain.getUserId())
                .username(domain.getUsername())
                .motivation(domain.getMotivation())
                .requirement(embeddable)
                .status(domain.getStatus())
                .appliedAt(domain.getAppliedAt())
                .build();
    }

    public static Application toDomain(ApplicationEntity applicationEntity) {
        PositionRequirementEmbeddable emb = applicationEntity.getRequirement();
        PositionRequirement requirement =
                PositionRequirement.of(
                        emb.getPosition(),
                        emb.getSkillLevel()
                );

        return Application.reconstruct(
                applicationEntity.getApplicationGuid(),
                applicationEntity.getProjectGuid(),
                applicationEntity.getUserId(),
                applicationEntity.getUsername(),
                applicationEntity.getMotivation(),
                requirement,
                applicationEntity.getStatus(),
                applicationEntity.getAppliedAt(),
                toAuditInfo(applicationEntity)
        );
    }

    private static AuditInfo toAuditInfo(ApplicationEntity applicationEntity) {
        return AuditInfo.of(
                applicationEntity.getCreatedBy(),
                applicationEntity.getCreatedAt(),
                applicationEntity.getModifiedBy(),
                applicationEntity.getModifiedAt()
        );
    }
}