package goonerd.devhub.ports.in.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.common.pagination.PageCommand;
import goonerd.devhub.applications.project.ProjectWithStatus;
import goonerd.devhub.domains.project.Project;
import org.springframework.data.domain.Page;

public interface ProjectUseCase {
    Page<ProjectWithStatus> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand);
    Project createProject(CreateProjectCommand createProjectCommand);
}