package goonerd.devhub.adapters.in.facade.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.project.dto.ProjectResponseDto;
import goonerd.devhub.adapters.in.vo.PageCommand;
import goonerd.devhub.adapters.in.vo.PageVo;
import goonerd.devhub.common.converter.PageConverter;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.in.ProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectUseCase projectUseCase;

    public PageVo<ProjectResponseDto> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        Page<Project> projectPage = projectUseCase.listProject(searchProjectCommand, pageCommand);
        return PageConverter.convert(projectPage, ProjectResponseDto::fromDomain);
    }

    public ProjectResponseDto createProject(CreateProjectCommand createProjectCommand) {
        Project project = projectUseCase.createProject(createProjectCommand);
        return ProjectResponseDto.fromDomain(project);
    }
}