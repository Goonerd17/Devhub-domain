package goonerd.devhub.project.controller;

import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.project.dto.ProjectRequestDto;
import goonerd.devhub.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<?>> listProject() {
        return projectService.listProject();
    }

    @PostMapping()
    public ResponseEntity<ApiResponseVo<?>> createProject(@RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.createProject(projectRequestDto);
    }
}