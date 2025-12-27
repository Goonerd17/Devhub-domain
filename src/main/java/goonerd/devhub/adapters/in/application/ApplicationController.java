package goonerd.devhub.adapters.in.application;

import goonerd.devhub.adapters.in.application.command.ApplicationCommand;
import goonerd.devhub.adapters.in.application.dto.ApplicationRequestDto;
import goonerd.devhub.adapters.in.application.dto.ApplicationResponseDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.ports.in.application.ApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectGuid}/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationUseCase applicationUseCase;

    @PostMapping
    public ResponseEntity<ApiResponseVo<ApplicationResponseDto>> apply(@PathVariable String projectGuid, @RequestBody ApplicationRequestDto applicationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        ApplicationCommand applyApplicationCommand = ApplicationCommand.fromApplyApplicationRequestDto(projectGuid, applicationRequestDto, userDetailsImpl.getUserId());
        return ResponseEntity.ok(ApiResponseVo.
                successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        ApplicationResponseDto.fromDomain(applicationUseCase.applyApplication(applyApplicationCommand))
                )
        );
    }

    @PostMapping("/{applicationGuid}/approve")
    public ResponseEntity<ApiResponseVo<ApplicationResponseDto>> approve(@PathVariable String projectGuid, @PathVariable String applicationGuid, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        ApplicationCommand approveApplicationCommand = ApplicationCommand.fromApproveApplicationRequestDto(projectGuid, applicationGuid, userDetailsImpl.getUserId());
        return ResponseEntity.ok(ApiResponseVo.
                successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        ApplicationResponseDto.fromDomain(applicationUseCase.approveApplication(approveApplicationCommand))
                )
        );
    }
}