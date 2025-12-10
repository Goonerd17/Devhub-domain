package goonerd.devhub.user.controller;

import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.user.dto.SignupRequestDto;
import goonerd.devhub.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseVo<?>> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(userService.signup(signupRequestDto));
    }
}
