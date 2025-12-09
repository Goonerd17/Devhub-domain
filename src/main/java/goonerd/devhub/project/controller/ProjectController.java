package goonerd.devhub.project.controller;

import goonerd.devhub.common.dto.PageRequestDto;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.project.dto.ProjectRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<PageVo<ProjectResponseDto>>> listProject(PageRequestDto pageable) {
        return ResponseEntity.ok(projectService.listProject(pageable));
    }

    @PostMapping()
    public ResponseEntity<ApiResponseVo<ProjectResponseDto>> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto) {
        return ResponseEntity.ok(projectService.createProject(projectRequestDto));
    }
}