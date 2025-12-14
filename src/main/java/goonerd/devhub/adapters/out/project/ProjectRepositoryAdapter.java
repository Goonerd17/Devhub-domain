package goonerd.devhub.adapters.out.project;

import goonerd.devhub.adapters.in.vo.PageCommand;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectRepositoryJpa projectRepositoryJpa;
    private final PositionSlotRepositoryJpa positionSlotRepositoryJpa;

    @Override
    public Page<Project> listProject(PageCommand pageCommand) {
        Pageable pageable = pageCommand.toPageable();
        Page<ProjectEntity> pagedProjectEntity = projectRepositoryJpa.findAll(pageable);
        return pagedProjectEntity.map(savedProjectEntity -> {
            List<PositionSlotEntity> positionSlotEntityList = positionSlotRepositoryJpa.findByProjectGuid(savedProjectEntity.getProjectGuid())
                    .stream()
                    .toList();
            return ProjectMapper.toDomain(savedProjectEntity, positionSlotEntityList);
        });
    }

    @Override
    public Project createProject (Project project) {
        ProjectEntity savedProjectEntity = projectRepositoryJpa.save(ProjectMapper.toEntity(project));
        List<PositionSlotEntity> positionSlotEntityList = project.getPositionSlots().stream()
                .map(slot -> PositionSlotMapper.toEntity(savedProjectEntity.getProjectGuid(), slot))
                .toList();
        List<PositionSlotEntity> savedPositionSlotEntityList = positionSlotRepositoryJpa.saveAll(positionSlotEntityList);
        return ProjectMapper.toDomain(savedProjectEntity, savedPositionSlotEntityList);
    }

    @Override
    public Optional<Project> findByProjectGuId(String projectGuid) {
        return projectRepositoryJpa.findByProjectGuid(projectGuid)
                .map(savedProjectEntity -> {
                    List<PositionSlotEntity> positionSlotEntityList = positionSlotRepositoryJpa.findByProjectGuid(savedProjectEntity.getProjectGuid())
                            .stream()
                            .toList();
                    return ProjectMapper.toDomain(savedProjectEntity, positionSlotEntityList);
                });
    }
}
