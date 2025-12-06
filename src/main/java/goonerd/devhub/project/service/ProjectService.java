package goonerd.devhub.project.service;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.utils.ApiResponseUtil;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.project.dto.ProjectRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.entity.Project;
import goonerd.devhub.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ApiResponseUtil apiResponseUtil;

    public ResponseEntity<ApiResponseVo<?>> listProject() {
        try {
            List<ProjectResponseDto> projectResponseList = projectRepository.findAll().stream()
                    .map(ProjectResponseDto::fromEntity)
                    .toList();
            return apiResponseUtil.success(SuccessCodeEnum.PROJECT_READ_SUCCESS, Collections.emptyMap(), projectResponseList);
        } catch (Exception e) {
            return apiResponseUtil.fail(ErrorCodeEnum.PROJECT_READ_FAIL, Collections.emptyMap(), e.getMessage());
        } finally {
            log.info("---listProject---");
        }
    }

//    public ResponseEntity<ApiResponseVo<?>> findProject(ProjectRequestDto projectRequestDto) {
//        try {
//            projectRepository.find
//            return apiResponseUtil.success(SuccessCodeEnum.PROJECT_READ_SUCCESS, Collections.emptyMap(), guestBookResponseList);
//        } catch (Exception e) {
//            return apiResponseUtil.fail(ErrorCodeEnum.PROJECT_READ_FAIL, Collections.emptyMap(), e.getMessage());
//        } finally {
//            log.info("---findProject---");
//        }
//    }

    public ResponseEntity<ApiResponseVo<?>> createProject(ProjectRequestDto projectRequestDto) {
        try {
            Project project = Project.create(projectRequestDto);
            projectRepository.save(project);
            ProjectResponseDto projectResponseDto = ProjectResponseDto.fromEntity(project);
            return apiResponseUtil.success(SuccessCodeEnum.PROJECT_CREATE_SUCCESS, projectRequestDto, projectResponseDto);
        } catch (Exception e) {
            return apiResponseUtil.fail(ErrorCodeEnum.PROJECT_CREATE_FAIL, projectRequestDto, e.getMessage());
        } finally {
            log.info("---createProject---");
        }
    }
}
