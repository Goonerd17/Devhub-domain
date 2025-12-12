package goonerd.devhub.adapters.out.project;

import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryJpaAdapter implements ProjectRepository {

    private final ProjectRepositoryJpa projectRepositoryJpa;

    @Override
    public Page<Project> listProject(PageCommand pageCommand) {
        Pageable pageable = pageCommand.toPageable();

        Page<ProjectEntity> entityPage = projectRepositoryJpa.findAll(pageable);

        return entityPage.map(ProjectMapper::toDomain);
    }

    @Override
    public Project createProject (Project project) {
        ProjectEntity entity = ProjectMapper.toEntity(project);
        ProjectEntity saved = projectRepositoryJpa.save(entity);
        return ProjectMapper.toDomain(saved);
    }
}
