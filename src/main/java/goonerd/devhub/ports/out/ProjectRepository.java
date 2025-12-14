package goonerd.devhub.ports.out;

import goonerd.devhub.adapters.in.vo.PageCommand;
import goonerd.devhub.domain.project.Project;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProjectRepository {
    Page<Project> listProject(PageCommand pageCommand);
    Project createProject(Project project);
    Optional<Project> findByProjectGuId(String projectGuid);
}