package goonerd.devhub.adapters.out.position;

import goonerd.devhub.adapters.out.position.entity.PositionEntity;
import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domain.position.Position;
import goonerd.devhub.ports.out.position.PositionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PositionPortAdapter implements PositionPort {

    private final PositionRepositoryJpa positionRepositoryJpa;

    @Override
    public List<Position> findByProjectGuid(String projectGuid) {
        return positionRepositoryJpa.findByProjectEntity_ProjectGuid(projectGuid).stream()
                .map(PositionMapper::toDomain)
                .toList();
    }

    @Override
    public void saveAll(ProjectEntity projectEntity, List<Position> positionList) {
        List<PositionEntity> positionEntityList = positionList.stream()
                .map(position -> PositionMapper.toEntity(projectEntity, position))
                .toList();
        positionRepositoryJpa.saveAll(positionEntityList);
    }

    @Override
    public void deleteByProjectGuid(String projectGuid) {
        positionRepositoryJpa.deleteByProjectEntity_ProjectGuid(projectGuid);
    }
}
