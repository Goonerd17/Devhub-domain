package goonerd.devhub.adapters.out.project;

import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.domain.project.PositionSlot;
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
public class ProjectRepositoryJpaAdapter implements ProjectRepository {

    private final ProjectRepositoryJpa projectRepositoryJpa;
    private final PositionSlotRepositoryJpa positionSlotRepositoryJpa;

    @Override
    public Page<Project> listProject(PageCommand pageCommand) {
        Pageable pageable = pageCommand.toPageable();
        Page<ProjectEntity> projectEntityPage = projectRepositoryJpa.findAll(pageable);
        return projectEntityPage.map(savedEntity -> {
            List<PositionSlotEntity> positionSlotEntities = positionSlotRepositoryJpa.findByProjectGuid(savedEntity.getProjectGuid())
                    .stream()
                    .toList();
            return ProjectMapper.toDomain(savedEntity, positionSlotEntities);
        });
    }

    @Override
    public Project createProject (Project project) {
        ProjectEntity savedEntity = projectRepositoryJpa.save(ProjectMapper.toEntity(project));

        List<PositionSlotEntity> positionSlotEntities = positionSlotRepositoryJpa.findByProjectGuid(savedEntity.getProjectGuid())
                .stream()
                .toList();
        return ProjectMapper.toDomain(savedEntity, positionSlotEntities);
    }

    @Override
    public Optional<Project> findByProjectGuId(String projectGuid) {
        return projectRepositoryJpa.findByProjectGuid(projectGuid)
                .map(savedEntity -> {
                    List<PositionSlotEntity> positionSlotEntities = positionSlotRepositoryJpa.findByProjectGuid(savedEntity.getProjectGuid())
                            .stream()
                            .toList();
                    return ProjectMapper.toDomain(savedEntity, positionSlotEntities);
                });
    }
}
