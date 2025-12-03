package goonerd.devhub.guestbook.service;

import goonerd.devhub.common.devhubenum.ErrorCodeEnum;
import goonerd.devhub.common.devhubenum.SuccessCodeEnum;
import goonerd.devhub.common.utils.ApiResponseBuilder;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.guestbook.dto.ProjectRequestDto;
import goonerd.devhub.guestbook.dto.ProjectResponseDto;
import goonerd.devhub.guestbook.entity.Project;
import goonerd.devhub.guestbook.repository.ProjectRepository;
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
    private final ApiResponseBuilder apiResponseBuilder;

    public ResponseEntity<ApiResponseVo<?>> listGuestBook() {
        try {
            List<ProjectResponseDto> guestBookResponseList = projectRepository.findAll().stream()
                    .map(ProjectResponseDto::fromEntity)
                    .toList();
            return apiResponseBuilder.success(SuccessCodeEnum.GUESTBOOK_LIST_SUCCESS, Collections.emptyMap(), guestBookResponseList);
        } catch (Exception e) {
            return apiResponseBuilder.fail(ErrorCodeEnum.GUESTBOOK_LIST_FAIL, Collections.emptyMap(), e.getMessage());
        } finally {
            log.info("GuestBookService - listGuestBook is finished");
        }
    }

    public ResponseEntity<ApiResponseVo<?>> createGuestBook(ProjectRequestDto projectRequestDto) {
        try {
            Project projectEntity = projectRepository.save(projectRequestDto.toEntity());
            ProjectResponseDto projectResponseDto = ProjectResponseDto.fromEntity(projectEntity);
            return apiResponseBuilder.success(SuccessCodeEnum.GUESTBOOK_CREATE_SUCCESS, projectRequestDto, projectResponseDto);
        } catch (Exception e) {
            return apiResponseBuilder.fail(ErrorCodeEnum.GUESTBOOK_CREATE_FAIL, projectRequestDto, e.getMessage());
        } finally {
            log.info("param 1 {}, param 2 {} : ", projectRequestDto.getUsername(), projectRequestDto.getDescription());
            log.info("GuestBookService - createGuestBook is finished");
        }
    }
}
