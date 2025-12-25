package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.common.pagination.PageCommand;
import goonerd.devhub.application.project.ProjectWithStatus;
import goonerd.devhub.domain.project.Project;
import org.springframework.data.domain.Page;

public interface ProjectUseCase {
    Page<ProjectWithStatus> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand);
    Project createProject(CreateProjectCommand createProjectCommand);
}