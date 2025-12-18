package goonerd.devhub.adapters.out.project.project;

import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.vo.PageCommand;
import goonerd.devhub.adapters.out.project.position.PositionSlotEntity;
import goonerd.devhub.adapters.out.project.position.PositionSlotMapper;
import goonerd.devhub.adapters.out.project.position.PositionSlotRepositoryJpa;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectRepositoryJpa projectRepositoryJpa;
    private final ProjectQueryRepository projectQueryRepository;
    private final PositionSlotRepositoryJpa positionSlotRepositoryJpa;

    @Override
    public Page<Project> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        Pageable pageable = pageCommand.toPageable();
        Page<ProjectEntity> pagedProjectEntityList = projectQueryRepository.search(searchProjectCommand, pageable);

        List<ProjectEntity> projectEntitiyList = pagedProjectEntityList.getContent();
        if (projectEntitiyList.isEmpty()) {
            return Page.empty(pageable);
        }

        List<String> projectGuids = projectEntitiyList.stream()
                .map(ProjectEntity::getProjectGuid)
                .toList();
        List<PositionSlotEntity> slotEntities =
                positionSlotRepositoryJpa.findByProjectEntity_ProjectGuidIn(projectGuids);
        Map<String, List<PositionSlotEntity>> slotEntityMap =
                slotEntities.stream()
                        .collect(Collectors.groupingBy(
                                slot -> slot.getProjectEntity().getProjectGuid()
                        ));
        List<Project> projects = projectEntitiyList.stream()
                .map(projectEntity ->
                        ProjectMapper.toDomain(
                                projectEntity,
                                slotEntityMap.getOrDefault(
                                        projectEntity.getProjectGuid(),
                                        List.of()
                                )
                        )
                )
                .toList();

        return new PageImpl<>(
                projects,
                pageable,
                pagedProjectEntityList.getTotalElements()
        );
    }

    @Override
    public Project createProject (Project project) {
        ProjectEntity savedProjectEntity = projectRepositoryJpa.save(ProjectMapper.toEntity(project));

        List<PositionSlotEntity> positionSlotEntityList = project.getPositionSlots().stream()
                .map(positionSlot -> PositionSlotMapper.toEntity(savedProjectEntity, positionSlot))
                .toList();

        List<PositionSlotEntity> savedPositionSlotEntityList = positionSlotRepositoryJpa.saveAll(positionSlotEntityList);
        return ProjectMapper.toDomain(savedProjectEntity, savedPositionSlotEntityList);
    }

    @Override
    public Optional<Project> findByProjectGuId(String projectGuid) {
        return projectRepositoryJpa.findByProjectGuid(projectGuid)
                .map(savedProjectEntity -> {
                    List<PositionSlotEntity> positionSlotEntityList = positionSlotRepositoryJpa.findByProjectEntity_ProjectGuid(savedProjectEntity.getProjectGuid())
                            .stream()
                            .toList();
                    return ProjectMapper.toDomain(savedProjectEntity, positionSlotEntityList);
                });
    }
}
