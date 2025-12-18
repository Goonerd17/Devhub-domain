package goonerd.devhub.adapters.out.project.position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionSlotRepositoryJpa extends JpaRepository<PositionSlotEntity, String> {
    List<PositionSlotEntity> findByProjectEntity_ProjectGuid(String projectGuid);
    void deleteByProjectEntity_ProjectGuid(String projectGuid);
    List<PositionSlotEntity> findByProjectEntity_ProjectGuidIn(List<String> projectGuids);
}
