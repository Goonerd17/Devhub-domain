package goonerd.devhub.ports.out.project;

import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.common.pagination.PageCommand;
import goonerd.devhub.domain.project.Project;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProjectRepository {
    Page<Project> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand);
    Project save(Project project);
    Optional<Project> findByProjectGuId(String projectGuid);
}