package goonerd.devhub.adapters.out.application;

import goonerd.devhub.domain.application.Application;
import goonerd.devhub.domain.application.PositionRequirement;

public class ApplicationMapper {

    // Domain → Entity
    public static ApplicationEntity toEntity(Application domain) {
        PositionRequirement req = domain.getRequirement();

        PositionRequirementEmbeddable embeddable =
                new PositionRequirementEmbeddable(
                        req.position(),
                        req.skillLevel()
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

    // Entity → Domain
    public static Application toDomain(ApplicationEntity entity) {
        PositionRequirementEmbeddable emb = entity.getRequirement();

        PositionRequirement requirement = new PositionRequirement(
                emb.getPosition(),
                emb.getSkillLevel()
        );
        return Application.reconstruct(
                entity.getApplicationGuid(),
                entity.getProjectGuid(),
                entity.getUserId(),
                entity.getUsername(),
                entity.getMotivation(),
                requirement,
                entity.getStatus(),
                entity.getAppliedAt()
        );
    }
}
