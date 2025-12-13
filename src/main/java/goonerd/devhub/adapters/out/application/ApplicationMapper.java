package goonerd.devhub.adapters.out.application;

import goonerd.devhub.domain.application.Application;

public class ApplicationMapper {

    // Domain → Entity
    public static ApplicationEntity toEntity(Application domain) {
        return ApplicationEntity.builder()
                .applicationGuid(domain.getApplicationGuid())
                .projectGuid(domain.getProjectGuid())
                .userId(domain.getUserId())
                .username(domain.getUsername())
                .motivation(domain.getMotivation())
                .position(domain.getPosition())
                .proficiency(domain.getProficiency())
                .status(domain.getStatus())
                .appliedAt(domain.getAppliedAt())
                .build();
    }

    // Entity → Domain
    public static Application toDomain(ApplicationEntity entity) {
        return Application.reconstruct(
                entity.getApplicationGuid(),
                entity.getProjectGuid(),
                entity.getUserId(),
                entity.getUsername(),
                entity.getMotivation(),
                entity.getPosition(),
                entity.getProficiency(),
                entity.getStatus(),
                entity.getAppliedAt()
        );
    }
}
