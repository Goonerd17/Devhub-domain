package goonerd.devhub.adapters.in.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.adapters.in.submission.dto.SubmissionRequestDto;
import goonerd.devhub.adapters.in.submission.dto.SubmissionResponseDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.ports.in.submission.SubmissionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectGuid}/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionUseCase submissionUseCase;

    @PostMapping
    public ResponseEntity<ApiResponseVo<SubmissionResponseDto>> apply(@PathVariable String projectGuid, @RequestBody SubmissionRequestDto submissionRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        SubmissionCommand submissionApplyCommand = SubmissionCommand.fromApplySubmissionRequestDto(projectGuid, submissionRequestDto, userDetailsImpl.getUserId());
        return ResponseEntity.ok(ApiResponseVo.
                successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        SubmissionResponseDto.fromDomain(submissionUseCase.applySubmission(submissionApplyCommand))
                )
        );
    }

    @PostMapping("/{submissionGuid}/approve")
    public ResponseEntity<ApiResponseVo<SubmissionResponseDto>> approve(@PathVariable String projectGuid, @PathVariable String submissionGuid, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        SubmissionCommand submissionApproveCommand = SubmissionCommand.fromApproveSubmissionRequestDto(projectGuid, submissionGuid, userDetailsImpl.getUserId());
        return ResponseEntity.ok(ApiResponseVo.
                successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        SubmissionResponseDto.fromDomain(submissionUseCase.approveSubmission(submissionApproveCommand))
                )
        );
    }
}