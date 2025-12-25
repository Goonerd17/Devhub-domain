package goonerd.devhub.adapters.in.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.project.dto.CreateProjectRequestDto;
import goonerd.devhub.adapters.in.project.dto.ProjectResponseDto;
import goonerd.devhub.adapters.in.project.dto.SearchProjectRequestDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.adapters.in.common.pagination.PageCommand;
import goonerd.devhub.adapters.in.common.pagination.PageRequestDto;
import goonerd.devhub.adapters.in.common.vo.PageVo;
import goonerd.devhub.application.project.ProjectWithStatus;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.adapters.in.common.pagination.PageConverter;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.in.project.ProjectUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Tag(name = "Project API", description = "프로젝트 API")
public class ProjectController {

    private final ProjectUseCase projectUseCase;

    @Operation(
            summary = "프로젝트 목록 조회",
            description = "검색 조건과 페이징 정보에 따라 프로젝트 목록을 조회합니다.",
            security = {@SecurityRequirement(name = "BearerAuth")}
    )
    @GetMapping()
    public ResponseEntity<ApiResponseVo<ProjectResponseDto>> listProject(@RequestBody SearchProjectRequestDto searchProjectRequestDto, PageRequestDto pageRequestDto) {
        SearchProjectCommand searchProjectCommand = SearchProjectCommand.fromProjectSearchRequestDto(searchProjectRequestDto);
        PageCommand pageCommand = PageCommand.of(pageRequestDto);

        Page<ProjectWithStatus> projectWithStatusPage = projectUseCase.listProject(searchProjectCommand, pageCommand);
        List<ProjectResponseDto> dataList = PageConverter.toList(projectWithStatusPage, ProjectResponseDto::fromDomainReadModel);
        PageVo pageVo = PageConverter.toPageVo(projectWithStatusPage);
        return ResponseEntity.ok(ApiResponseVo.successWithDataList(SuccessCodeEnum.READ_SUCCESS, dataList, pageVo));
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
    public ResponseEntity<ApiResponseVo<ProjectResponseDto>> createProject(@Valid @RequestBody CreateProjectRequestDto createProjectRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        CreateProjectCommand createProjectCommand = CreateProjectCommand.fromCreateProjectRequestDto(createProjectRequestDto, userDetailsImpl.getUserId());
        Project project = projectUseCase.createProject(createProjectCommand);
        return ResponseEntity.ok(
                ApiResponseVo.successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        ProjectResponseDto.fromDomain(project)
                )
        );
    }
}