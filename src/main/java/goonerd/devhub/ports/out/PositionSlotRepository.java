package goonerd.devhub.ports.out;

import goonerd.devhub.domain.project.PositionSlot;

import java.util.List;

public interface PositionSlotRepository {
    List<PositionSlot> findByProjectGuid(String projectGuid);
    void saveAll(String projectGuid, List<PositionSlot> slots);
    void deleteByProjectGuid(String projectGuid);
}