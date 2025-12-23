package goonerd.devhub.common.auth.controller;

import goonerd.devhub.adapters.in.vo.ApiResponseVo;
import goonerd.devhub.common.auth.service.AuthService;
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
    public ResponseEntity<ApiResponseVo<?>> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
        String newAccessToken = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(
                ApiResponseVo.successWithoutParamAndData(
                        SuccessCodeEnum.CREATE_SUCCESS
                )
        );
    }
}
