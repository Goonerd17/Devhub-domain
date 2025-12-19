package goonerd.devhub.adapters.out.project.position;

import goonerd.devhub.adapters.out.project.project.ProjectEntity;
import goonerd.devhub.domain.project.PositionSlot;

public class PositionSlotMapper {

    public static PositionSlotEntity toEntity(ProjectEntity projectEntity, PositionSlot positionSlot) {
        return PositionSlotEntity.builder()
                .projectEntity(projectEntity)
                .position(positionSlot.getPosition())
                .proficiency(positionSlot.getProficiency())
                .capacity(positionSlot.getCapacity())
                .approvedCount(positionSlot.getApprovedCount())
                .build();
    }

    public static PositionSlot toDomain(PositionSlotEntity positionSlotEntity) {
        PositionSlot positionSlot = PositionSlot.createPositionSlot(
                positionSlotEntity.getPosition(),
                positionSlotEntity.getProficiency(),
                positionSlotEntity.getCapacity()
        );
        positionSlot.assignProject(positionSlotEntity.getProjectEntity().getProjectGuid());
        return positionSlot;
    }
}
