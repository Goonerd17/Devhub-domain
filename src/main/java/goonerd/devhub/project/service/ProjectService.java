package goonerd.devhub.project.service;

import goonerd.devhub.common.vo.PageRequestVo;
import goonerd.devhub.project.command.ProjectCreateCommand;
import goonerd.devhub.project.command.ProjectSearchCommand;
import goonerd.devhub.project.dto.ProjectCreateRequestDto;
import goonerd.devhub.project.entity.Project;
import goonerd.devhub.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<Project> listProject(ProjectSearchCommand projectSearchCommand, PageRequestVo pageable) {
        return projectRepository.findAll(pageable.toPageable());
    }

    public Project createProject(ProjectCreateCommand projectCreateCommand) {
        Project project = Project.create(projectCreateCommand);
        return projectRepository.save(project);
    }
}