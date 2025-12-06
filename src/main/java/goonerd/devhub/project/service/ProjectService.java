package goonerd.devhub.project.service;

import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.project.dto.ProjectRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.entity.Project;
import goonerd.devhub.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ApiResponseVo<List<ProjectResponseDto>> listProject() {
        List<ProjectResponseDto> projectResponseList = projectRepository.findAll().stream()
                .map(ProjectResponseDto::fromEntity)
                .toList();
        return ApiResponseVo.success(SuccessCodeEnum.READ_SUCCESS,Collections.emptyMap(), projectResponseList);
    }

    public ApiResponseVo<ProjectResponseDto> createProject(ProjectRequestDto projectRequestDto) {
        Project project = Project.create(projectRequestDto);
        projectRepository.save(project);
        ProjectResponseDto projectResponseDto = ProjectResponseDto.fromEntity(project);
        return ApiResponseVo.success(SuccessCodeEnum.CREATE_SUCCESS, projectRequestDto, projectResponseDto);
    }
}