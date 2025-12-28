package goonerd.devhub.adapters.out.project;

import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.common.pagination.PageCommand;
import goonerd.devhub.adapters.out.position.entity.PositionEntity;
import goonerd.devhub.adapters.out.position.PositionMapper;
import goonerd.devhub.adapters.out.position.PositionRepositoryJpa;
import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domains.project.Project;
import goonerd.devhub.ports.out.project.ProjectPort;
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
public class ProjectAdapter implements ProjectPort {

    private final ProjectRepositoryJpa projectRepositoryJpa;
    private final ProjectQueryRepository projectQueryRepository;
    private final PositionRepositoryJpa positionRepositoryJpa;

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
        List<PositionEntity> positionEntityList =
                positionRepositoryJpa.findByProjectEntity_ProjectGuidIn(projectGuids);
        Map<String, List<PositionEntity>> positionEntityMap =
                positionEntityList.stream()
                        .collect(Collectors.groupingBy(
                                positionEntity -> positionEntity.getProjectEntity().getProjectGuid()
                        ));
        List<Project> projects = projectEntitiyList.stream()
                .map(projectEntity ->
                        ProjectMapper.toDomain(
                                projectEntity,
                                positionEntityMap.getOrDefault(
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
    public Project save(Project project) {
        ProjectEntity savedProjectEntity = projectRepositoryJpa.save(ProjectMapper.toEntity(project));

        List<PositionEntity> positionEntityList = project.getPositionList().stream()
                .map(position -> PositionMapper.toEntity(savedProjectEntity, position))
                .toList();

        List<PositionEntity> savedPositionEntityList = positionRepositoryJpa.saveAll(positionEntityList);
        return ProjectMapper.toDomain(savedProjectEntity, savedPositionEntityList);
    }

    @Override
    public Optional<Project> findByProjectGuId(String projectGuid) {
        return projectRepositoryJpa.findByProjectGuid(projectGuid)
                .map(savedProjectEntity -> {
                    List<PositionEntity> positionEntityList = positionRepositoryJpa.findByProjectEntity_ProjectGuid(savedProjectEntity.getProjectGuid())
                            .stream()
                            .toList();
                    return ProjectMapper.toDomain(savedProjectEntity, positionEntityList);
                });
    }
}