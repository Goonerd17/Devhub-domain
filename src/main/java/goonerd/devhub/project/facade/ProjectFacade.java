package goonerd.devhub.project.facade;

import goonerd.devhub.common.converter.PageConverter;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageRequestVo;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.project.command.ProjectCreateCommand;
import goonerd.devhub.project.command.ProjectSearchCommand;
import goonerd.devhub.project.dto.ProjectCreateRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.dto.ProjectSearchRequestDto;
import goonerd.devhub.project.entity.Project;
import goonerd.devhub.project.service.ProjectService;
import goonerd.devhub.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;
    private final UserService userService;

    public ApiResponseVo<PageVo<ProjectResponseDto>> listProject(ProjectSearchRequestDto projectSearchRequestDto, PageRequestVo pageRequestVo) {
        ProjectSearchCommand projectSearchCommand = ProjectSearchCommand.fromProjectSearchRequestDto(projectSearchRequestDto);
        Page<Project> projectPage = projectService.listProject(projectSearchCommand, pageRequestVo);
        PageVo<ProjectResponseDto> projectResponse = PageConverter.convert(projectPage, ProjectResponseDto::fromEntity);
        return ApiResponseVo.successWithData(SuccessCodeEnum.READ_SUCCESS, projectResponse);
    }

    public ApiResponseVo<ProjectResponseDto> createProject(ProjectCreateRequestDto projectCreateRequestDto) {
        ProjectCreateCommand projectCreateCommand = ProjectCreateCommand.fromProjectCreateRequestDto(projectCreateRequestDto);
        Project project = projectService.createProject(projectCreateCommand);
        ProjectResponseDto projectResponseDto = ProjectResponseDto.fromEntity(project);
        return ApiResponseVo.successWithParamAndData(SuccessCodeEnum.CREATE_SUCCESS, projectCreateRequestDto, projectResponseDto);
    }
}
