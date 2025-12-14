package goonerd.devhub.adapters.in.submission;

import goonerd.devhub.adapters.in.facade.submission.SubmissionFacade;
import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.adapters.in.submission.dto.SubmissionRequestDto;
import goonerd.devhub.adapters.in.submission.dto.SubmissionResponseDto;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.adapters.in.vo.ApiResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/{projectGuid}/applications")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionFacade submissionFacade;

    @PostMapping
    public ResponseEntity<ApiResponseVo<SubmissionResponseDto>> apply(@PathVariable String projectGuid, @RequestBody SubmissionRequestDto submissionRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        SubmissionCommand submissionCommand = SubmissionCommand.fromApplyApplicationRequestDto(projectGuid, submissionRequestDto, userDetailsImpl.getUserId());
        return ResponseEntity.ok(ApiResponseVo.
                successWithParamAndData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        submissionCommand, submissionFacade.apply(submissionCommand)
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