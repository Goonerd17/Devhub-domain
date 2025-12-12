package goonerd.devhub.ports.out;

import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.domain.project.Project;
import org.springframework.data.domain.Page;

public interface ProjectRepository {
    Page<Project> listProject(PageCommand pageCommand);
    Project createProject(Project project);
}
