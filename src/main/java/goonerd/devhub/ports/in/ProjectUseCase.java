package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.project.dto.ProjectResponseDto;
import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.common.vo.PageVo;

public interface ProjectUseCase {
    PageVo<ProjectResponseDto> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand);
    ProjectResponseDto createProject(CreateProjectCommand createProjectCommand);
}