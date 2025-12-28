package goonerd.devhub.ports.out.position;

import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domains.position.Position;

import java.util.List;

public interface PositionPort {
    List<Position> findByProjectGuid(String projectGuid);
    void saveAll(ProjectEntity projectEntity, List<Position> positionList);
    void deleteByProjectGuid(String projectGuid);
}