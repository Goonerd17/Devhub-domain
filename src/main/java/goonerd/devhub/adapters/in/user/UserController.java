package goonerd.devhub.adapters.in.user;

import goonerd.devhub.adapters.in.user.dto.SignupUserCommand;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.adapters.in.user.dto.SignupRequestDto;
import goonerd.devhub.ports.in.UserUseCase;
import goonerd.devhub.service.user.UserService;
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

    private final UserUseCase userUseCase;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseVo<?>> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        SignupUserCommand signupUserCommand = SignupUserCommand.createSignupUserCommand(signupRequestDto);
        return ResponseEntity.ok(userUseCase.signup(signupUserCommand));
    }
}