package goonerd.devhub.adapters.out.position;

import goonerd.devhub.adapters.out.position.entity.PositionEntity;
import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domains.position.Position;

public class PositionMapper {

    public static PositionEntity toEntity(ProjectEntity projectEntity, Position position) {
        return PositionEntity.builder()
                .positionGuid(position.getPositionGuid())
                .projectId(projectEntity.getProjectGuid())
                .positionName(position.getPositionName())
                .capacity(position.getCapacity())
                .level(position.getLevel())
                .approvedCount(position.getApprovedCount())
                .projectEntity(projectEntity)
                .build();
    }

    public static Position toDomain(PositionEntity positionEntity) {
        return Position.createPosition(
                positionEntity.getPositionGuid(),
                positionEntity.getProjectId(),
                positionEntity.getPositionName(),
                positionEntity.getCapacity(),
                positionEntity.getLevel(),
                positionEntity.getApprovedCount()
        );
    }
}
