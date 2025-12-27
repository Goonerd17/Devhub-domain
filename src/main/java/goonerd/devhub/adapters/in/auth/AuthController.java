package goonerd.devhub.adapters.in.auth;

import goonerd.devhub.adapters.in.auth.dto.TokenResponseDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.adapters.in.auth.command.ConfirmEmailVerificationCommand;
import goonerd.devhub.adapters.in.auth.dto.ConfirmEmailVerificationRequestDto;
import goonerd.devhub.adapters.in.auth.dto.EmailVerificationRequestDto;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.ports.in.auth.AuthUseCase;
import goonerd.devhub.ports.in.mail.EmailVerificationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;
    private final EmailVerificationUseCase emailVerificationUseCase;

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponseVo<TokenResponseDto>> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return ResponseEntity.ok(
                ApiResponseVo.successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        authUseCase.refreshAccessToken(refreshToken)
                )
        );
    }

    @PostMapping("/email-verification")
    public ResponseEntity<ApiResponseVo<Void>> requestEmailVerificationCode(@Valid @RequestBody EmailVerificationRequestDto emailVerificationRequestDto) {
        emailVerificationUseCase.requestEmailVerificationCode(emailVerificationRequestDto);
        return ResponseEntity.ok(
                ApiResponseVo.successWithoutData(
                        SuccessCodeEnum.EMAIL_VERIFICATION_SENT
                )
        );
    }

    @PostMapping("/email-verification/confirm")
    public ResponseEntity<ApiResponseVo<Void>> verifyEmailVerificationCode(@Valid @RequestBody ConfirmEmailVerificationRequestDto confirmEmailVerificationRequestDto) {
        ConfirmEmailVerificationCommand confirmEmailVerificationCommand = ConfirmEmailVerificationCommand.of(confirmEmailVerificationRequestDto.getEmail(), confirmEmailVerificationRequestDto.getCode());
        emailVerificationUseCase.verifyEmailVerificationCode(confirmEmailVerificationCommand);
        return ResponseEntity.ok(
                ApiResponseVo.successWithoutData(
                        SuccessCodeEnum.EMAIL_VERIFICATION_SUCCESS
                )
        );
    }
}
