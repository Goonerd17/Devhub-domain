package goonerd.devhub.project.controller;

import goonerd.devhub.common.vo.PageRequestVo;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.project.dto.ProjectCreateRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.dto.ProjectSearchRequestDto;
import goonerd.devhub.project.facade.ProjectFacade;
import goonerd.devhub.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<PageVo<ProjectResponseDto>>> listProject(ProjectSearchRequestDto projectSearchRequestDto, PageRequestVo pageable) {
        return ResponseEntity.ok(projectFacade.listProject(projectSearchRequestDto, pageable));
    }

    @PostMapping()
    public ResponseEntity<ApiResponseVo<ProjectResponseDto>> createProject(@Valid @RequestBody ProjectCreateRequestDto projectCreateRequestDto) {
        return ResponseEntity.ok(projectFacade.createProject(projectCreateRequestDto));
    }
}