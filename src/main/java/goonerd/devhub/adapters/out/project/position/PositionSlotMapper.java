package goonerd.devhub.adapters.out.project.position;

import goonerd.devhub.domain.project.PositionSlot;

import java.util.ArrayList;

public class PositionSlotMapper {

    public static PositionSlotEntity toEntity(String projectGuid, PositionSlot positionSlot) {
        return PositionSlotEntity.builder()
                .projectGuid(projectGuid)
                .position(positionSlot.getPosition())
                .proficiency(positionSlot.getProficiency())
                .capacity(positionSlot.getCapacity())
                .acceptedUserIds(new ArrayList<>(positionSlot.getAcceptedUserIds()))
                .build();
    }

    public static PositionSlot toDomain(PositionSlotEntity positionSlotEntity) {
        return PositionSlot.of(
                positionSlotEntity.getPosition(),
                positionSlotEntity.getProficiency(),
                positionSlotEntity.getCapacity(),
                positionSlotEntity.getAcceptedUserIds()
        );
    }
}
