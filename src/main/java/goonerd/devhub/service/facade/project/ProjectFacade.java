package goonerd.devhub.service.facade.project;

import goonerd.devhub.adapters.in.project.dto.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.dto.ProjectResponseDto;
import goonerd.devhub.adapters.in.project.dto.SearchProjectCommand;
import goonerd.devhub.common.converter.PageConverter;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.in.ProjectUseCase;
import goonerd.devhub.service.project.ProjectService;
import goonerd.devhub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectFacade implements ProjectUseCase {

    private final ProjectService projectService;
    private final UserService userService;

    public ApiResponseVo<PageVo<ProjectResponseDto>> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        Page<Project> projectPage = projectService.listProject(searchProjectCommand, pageCommand);
        PageVo<ProjectResponseDto> projectResponse = PageConverter.convert(projectPage, ProjectResponseDto::fromDomain);
        return ApiResponseVo.successWithData(SuccessCodeEnum.READ_SUCCESS, projectResponse);
    }

    public ApiResponseVo<ProjectResponseDto> createProject(CreateProjectCommand createProjectCommand) {
        Project project = projectService.createProject(createProjectCommand);
        ProjectResponseDto projectResponseDto = ProjectResponseDto.fromDomain(project);
        return ApiResponseVo.successWithParamAndData(SuccessCodeEnum.CREATE_SUCCESS, createProjectCommand, projectResponseDto);
    }
}
