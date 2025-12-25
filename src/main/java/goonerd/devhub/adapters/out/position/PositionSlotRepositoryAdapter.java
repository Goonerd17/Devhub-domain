package goonerd.devhub.adapters.out.position;

import goonerd.devhub.adapters.out.position.entity.PositionSlotEntity;
import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.ports.out.position.PositionSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PositionSlotRepositoryAdapter implements PositionSlotRepository {

    private final PositionSlotRepositoryJpa positionSlotRepositoryJpa;

    @Override
    public List<PositionSlot> findByProjectGuid(String projectGuid) {
        return positionSlotRepositoryJpa.findByProjectEntity_ProjectGuid(projectGuid).stream()
                .map(PositionSlotMapper::toDomain)
                .toList();
    }

    @Override
    public void saveAll(ProjectEntity projectEntity, List<PositionSlot> positionSlotList) {
        List<PositionSlotEntity> positionSlotEntities = positionSlotList.stream()
                .map(slot -> PositionSlotMapper.toEntity(projectEntity, slot))
                .toList();
        positionSlotRepositoryJpa.saveAll(positionSlotEntities);
    }

    @Override
    public void deleteByProjectGuid(String projectGuid) {
        positionSlotRepositoryJpa.deleteByProjectEntity_ProjectGuid(projectGuid);
    }
}
