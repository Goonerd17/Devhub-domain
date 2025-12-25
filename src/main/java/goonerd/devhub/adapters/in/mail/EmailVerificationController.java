package goonerd.devhub.adapters.in.mail;

import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.adapters.in.mail.command.ConfirmEmailVerificationCommand;
import goonerd.devhub.adapters.in.mail.dto.ConfirmEmailVerificationRequestDto;
import goonerd.devhub.adapters.in.mail.dto.EmailVerificationRequestDto;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.ports.in.EmailVerificationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email-verifications")
public class EmailVerificationController {

    private final EmailVerificationUseCase emailVerificationUseCase;

    @PostMapping()
    public ResponseEntity<ApiResponseVo<Void>> requestEmailVerificationCode(@Valid @RequestBody EmailVerificationRequestDto emailVerificationRequestDto) {
        emailVerificationUseCase.requestEmailVerificationCode(emailVerificationRequestDto);
        return ResponseEntity.ok(
                ApiResponseVo.successWithoutData(
                        SuccessCodeEnum.EMAIL_VERIFICATION_SENT
                )
        );
    }

    @PostMapping("/{email}")
    public ResponseEntity<ApiResponseVo<Void>> verifyEmailVerificationCode(@PathVariable String email, @Valid @RequestBody ConfirmEmailVerificationRequestDto confirmEmailVerificationRequestDto) {
        ConfirmEmailVerificationCommand confirmEmailVerificationCommand = ConfirmEmailVerificationCommand.of(email, confirmEmailVerificationRequestDto.getCode());
        emailVerificationUseCase.verifyEmailVerificationCode(confirmEmailVerificationCommand);
        return ResponseEntity.ok(
                ApiResponseVo.successWithoutData(
                        SuccessCodeEnum.EMAIL_VERIFICATION_SUCCESS
                )
        );
    }
}