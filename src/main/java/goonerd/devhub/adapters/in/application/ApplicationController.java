package goonerd.devhub.adapters.in.application;

import goonerd.devhub.adapters.in.application.command.ApplyApplicationCommand;
import goonerd.devhub.adapters.in.application.dto.ApplyApplicationRequestDto;
import goonerd.devhub.adapters.in.application.dto.ApplyApplicationResponseDto;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.ports.in.ApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{projectGuid}/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationUseCase applicationUseCase;

    @PostMapping
    public ResponseEntity<ApiResponseVo<ApplyApplicationResponseDto>> apply(@PathVariable String projectGuid, @RequestBody ApplyApplicationRequestDto applyApplicationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        ApplyApplicationCommand applyApplicationCommand = ApplyApplicationCommand.fromApplyApplicationRequestDto(projectGuid, applyApplicationRequestDto, userDetailsImpl.getUserId());
        return ResponseEntity.ok(ApiResponseVo.
                successWithParamAndData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        applyApplicationCommand, applicationUseCase.apply(applyApplicationCommand)
                )
        );
    }

//    @PostMapping("/{applicationId}/approve")
//    public void approve(@PathVariable String applicationId) {
//        applicationUseCase.approve(applicationId);
//    }
//
//    @PostMapping("/{applicationId}/reject")
//    public void reject(@PathVariable String applicationId) {
//        applicationUseCase.reject(applicationId);
//    }
//
//    @PostMapping("/{applicationId}/cancel")
//    public void cancel(@PathVariable String applicationId) {
//        applicationUseCase.cancel(applicationId);
//    }
}