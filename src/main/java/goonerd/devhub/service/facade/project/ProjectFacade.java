package goonerd.devhub.service.facade.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.project.dto.ProjectResponseDto;
import goonerd.devhub.common.converter.PageConverter;
import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.in.ProjectUseCase;
import goonerd.devhub.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectFacade implements ProjectUseCase {

    private final ProjectService projectService;

    public PageVo<ProjectResponseDto> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        Page<Project> projectPage = projectService.listProject(searchProjectCommand, pageCommand);
        return PageConverter.convert(projectPage, ProjectResponseDto::fromDomain);
    }

    public ProjectResponseDto createProject(CreateProjectCommand createProjectCommand) {
        Project project = projectService.createProject(createProjectCommand);
        return ProjectResponseDto.fromDomain(project);
    }
}