package goonerd.devhub.adapters.out.position;

import goonerd.devhub.adapters.out.position.entity.PositionSlotEntity;
import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domain.project.PositionSlot;

public class PositionSlotMapper {

    public static PositionSlotEntity toEntity(ProjectEntity projectEntity, PositionSlot positionSlot) {
        return PositionSlotEntity.builder()
                .projectEntity(projectEntity)
                .position(positionSlot.getPosition())
                .capacity(positionSlot.getCapacity())
                .level(positionSlot.getLevel())
                .approvedCount(positionSlot.getApprovedCount())
                .build();
    }

    public static PositionSlot toDomain(PositionSlotEntity positionSlotEntity) {
        PositionSlot positionSlot = PositionSlot.createPositionSlot(
                positionSlotEntity.getPosition(),
                positionSlotEntity.getCapacity(),
                positionSlotEntity.getLevel()
        );
        positionSlot.assignProject(positionSlotEntity.getProjectEntity().getProjectGuid());
        return positionSlot;
    }
}
