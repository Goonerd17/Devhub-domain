package goonerd.devhub.service.project;

import goonerd.devhub.adapters.in.project.dto.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.dto.SearchProjectCommand;
import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<Project> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        return projectRepository.listProject(pageCommand);
    }

    public Project createProject(CreateProjectCommand createProjectCommand) {
        Project project = Project.createNew(
                createProjectCommand.getUserId(),
                createProjectCommand.getUsername(),
                createProjectCommand.getTitle(),
                createProjectCommand.getContent(),
                createProjectCommand.getRecruitmentType(),
                createProjectCommand.getDeliveryType(),
                createProjectCommand.getRecruitCount(),
                createProjectCommand.getStartDate(),
                createProjectCommand.getEndDate(),
                createProjectCommand.getPositions(),
                createProjectCommand.getSkills()
        );
        return projectRepository.createProject(project);
    }
}