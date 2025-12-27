package goonerd.devhub.adapters.out.position;

import goonerd.devhub.adapters.out.position.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepositoryJpa extends JpaRepository<PositionEntity, String> {
    List<PositionEntity> findByProjectEntity_ProjectGuid(String projectGuid);
    void deleteByProjectEntity_ProjectGuid(String projectGuid);
    List<PositionEntity> findByProjectEntity_ProjectGuidIn(List<String> projectGuids);
}
