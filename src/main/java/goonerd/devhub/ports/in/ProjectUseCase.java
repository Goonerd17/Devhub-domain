package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.dto.ProjectResponseDto;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.common.vo.PageVo;

public interface ProjectUseCase {
    ApiResponseVo<PageVo<ProjectResponseDto>> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand);
    ApiResponseVo<ProjectResponseDto> createProject(CreateProjectCommand createProjectCommand);
}