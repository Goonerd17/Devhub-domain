package goonerd.devhub.adapters.in.auth;

import goonerd.devhub.adapters.in.auth.dto.TokenResponseDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.application.auth.AuthService;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponseVo<TokenResponseDto>> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return ResponseEntity.ok(
                ApiResponseVo.successWithData(
                        SuccessCodeEnum.CREATE_SUCCESS,
                        authService.refreshAccessToken(refreshToken)
                )
        );
    }
}
