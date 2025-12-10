package goonerd.devhub.project.controller;

import goonerd.devhub.common.vo.PageRequestVo;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.common.vo.PageVo;
import goonerd.devhub.project.dto.ProjectCreateRequestDto;
import goonerd.devhub.project.dto.ProjectResponseDto;
import goonerd.devhub.project.dto.ProjectSearchRequestDto;
import goonerd.devhub.project.facade.ProjectFacade;
import goonerd.devhub.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Tag(name = "Project API", description = "프로젝트 API")
public class ProjectController {

    private final ProjectFacade projectFacade;

    @Operation(
            summary = "프로젝트 목록 조회",
            description = "검색 조건과 페이징 정보에 따라 프로젝트 목록을 조회합니다.",
            security = {@SecurityRequirement(name = "BearerAuth")}
    )
    @Parameters({
            @Parameter(name = "title", description = "프로젝트 제목 검색"),
            @Parameter(name = "username", description = "등록자 검색"),
            @Parameter(name = "page", description = "페이지 번호(기본 0)"),
            @Parameter(name = "size", description = "페이지 사이즈(기본 10)")
    })
    @GetMapping()
    public ResponseEntity<ApiResponseVo<PageVo<ProjectResponseDto>>> listProject(@ParameterObject ProjectSearchRequestDto projectSearchRequestDto, @ParameterObject PageRequestVo pageable) {
        return ResponseEntity.ok(projectFacade.listProject(projectSearchRequestDto, pageable));
    }

    @Operation(
            summary = "프로젝트 생성",
            description = "프로젝트 생성에 필요한 필수 정보를 전달하여 프로젝트를 생성합니다.",
            security = {@SecurityRequirement(name = "BearerAuth")}
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 생성 성공",
            content = @Content(schema = @Schema(implementation = ProjectResponseDto.class))
    )
    @PostMapping()
    public ResponseEntity<ApiResponseVo<ProjectResponseDto>> createProject(@Valid @RequestBody ProjectCreateRequestDto projectCreateRequestDto) {
        return ResponseEntity.ok(projectFacade.createProject(projectCreateRequestDto));
    }
}