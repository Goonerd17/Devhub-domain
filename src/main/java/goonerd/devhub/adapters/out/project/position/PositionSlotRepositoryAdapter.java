package goonerd.devhub.adapters.out.project.position;

import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.ports.out.PositionSlotRepository;
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
        return positionSlotRepositoryJpa.findByProjectGuid(projectGuid).stream()
                .map(PositionSlotMapper::toDomain)
                .toList();
    }

    @Override
    public void saveAll(String projectGuid, List<PositionSlot> positionSlotList) {
        List<PositionSlotEntity> positionSlotEntities = positionSlotList.stream()
                .map(slot -> PositionSlotMapper.toEntity(projectGuid, slot))
                .toList();
        positionSlotRepositoryJpa.saveAll(positionSlotEntities);
    }

    @Override
    public void deleteByProjectGuid(String projectGuid) {
        positionSlotRepositoryJpa.deleteByProjectGuid(projectGuid);
    }
}
