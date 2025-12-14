package goonerd.devhub.adapters.out.project;

import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.ports.out.PositionSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PositionSlotRepositoryJpaAdapter implements PositionSlotRepository {

    private final PositionSlotRepositoryJpa positionSlotRepositoryJpa;

    @Override
    public List<PositionSlot> findByProjectGuid(String projectGuid) {
        return positionSlotRepositoryJpa.findByProjectGuid(projectGuid).stream()
                .map(PositionSlotMapper::toDomain)
                .toList();
    }

    @Override
    public void saveAll(String projectGuid, List<PositionSlot> slots) {
        List<PositionSlotEntity> entities = slots.stream()
                .map(slot -> PositionSlotMapper.toEntity(projectGuid, slot))
                .toList();
        positionSlotRepositoryJpa.saveAll(entities);
    }

    @Override
    public void deleteByProjectGuid(String projectGuid) {
        positionSlotRepositoryJpa.deleteByProjectGuid(projectGuid);
    }
}
