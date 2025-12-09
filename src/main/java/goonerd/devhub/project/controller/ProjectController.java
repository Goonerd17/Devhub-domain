package goonerd.devhub.project.controller;

import goonerd.devhub.common.dto.PageRequestDto;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.project.dto.ProjectCreateRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.dto.ProjectSearchRequestDto;
import goonerd.devhub.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<PageVo<ProjectResponseDto>>> listProject(ProjectSearchRequestDto projectSearchRequestDto,PageRequestDto pageable) {
        return ResponseEntity.ok(projectService.listProject(projectSearchRequestDto, pageable));
    }

    @PostMapping()
    public ResponseEntity<ApiResponseVo<ProjectResponseDto>> createProject(@Valid @RequestBody ProjectCreateRequestDto projectCreateRequestDto) {
        return ResponseEntity.ok(projectService.createProject(projectCreateRequestDto));
    }
}