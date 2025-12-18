package goonerd.devhub.ports.out;

import goonerd.devhub.adapters.out.project.project.ProjectEntity;
import goonerd.devhub.domain.project.PositionSlot;

import java.util.List;

public interface PositionSlotRepository {
    List<PositionSlot> findByProjectGuid(String projectGuid);
    void saveAll(ProjectEntity projectEntity, List<PositionSlot> slots);
    void deleteByProjectGuid(String projectGuid);
}