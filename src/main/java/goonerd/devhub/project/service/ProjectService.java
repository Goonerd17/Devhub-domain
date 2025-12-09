package goonerd.devhub.project.service;

import goonerd.devhub.common.dto.PageRequestDto;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.converter.PageConverter;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.project.dto.ProjectRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.entity.Project;
import goonerd.devhub.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ApiResponseVo<PageVo<ProjectResponseDto>> listProject(PageRequestDto pageable) {
        Page<Project> projectPage = projectRepository.findAll(pageable.toPageable());
        PageVo<ProjectResponseDto> projectResponse = PageConverter.convert(projectPage, ProjectResponseDto::fromEntity);
        return ApiResponseVo.successWithData(SuccessCodeEnum.READ_SUCCESS, projectResponse);
    }

    public ApiResponseVo<ProjectResponseDto> createProject(ProjectRequestDto projectRequestDto) {
        Project project = Project.create(projectRequestDto);
        projectRepository.save(project);
        ProjectResponseDto projectResponseDto = ProjectResponseDto.fromEntity(project);
        return ApiResponseVo.successWithParamAndData(SuccessCodeEnum.CREATE_SUCCESS, projectRequestDto, projectResponseDto);
    }
}