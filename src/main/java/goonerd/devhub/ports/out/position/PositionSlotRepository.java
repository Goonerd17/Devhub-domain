package goonerd.devhub.ports.out.position;

import goonerd.devhub.adapters.out.project.project.entity.ProjectEntity;
import goonerd.devhub.domain.project.PositionSlot;

import java.util.List;

public interface PositionSlotRepository {
    List<PositionSlot> findByProjectGuid(String projectGuid);
    void saveAll(ProjectEntity projectEntity, List<PositionSlot> slots);
    void deleteByProjectGuid(String projectGuid);
}