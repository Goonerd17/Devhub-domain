package goonerd.devhub.adapters.out.project.position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionSlotRepositoryJpa extends JpaRepository<PositionSlotEntity, String> {
    List<PositionSlotEntity> findByProjectGuid(String projectGuid);
    void deleteByProjectGuid(String projectGuid);
}
