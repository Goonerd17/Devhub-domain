package goonerd.devhub.adapters.in.user;

import goonerd.devhub.adapters.in.user.dto.SignupUserRequestDto;
import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.in.UserUseCase;
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
    public ResponseEntity<ApiResponseVo<User>> signup(@Valid @RequestBody SignupUserRequestDto signupUserRequestDto) {
        SignupUserCommand signupUserCommand = SignupUserCommand.fromSignupUserRequestDto(signupUserRequestDto);
        return ResponseEntity.ok(userUseCase.signup(signupUserCommand));
    }
}