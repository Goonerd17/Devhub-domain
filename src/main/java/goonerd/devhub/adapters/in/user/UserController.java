package goonerd.devhub.adapters.in.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.adapters.in.user.dto.SignupUserRequestDto;
import goonerd.devhub.adapters.in.user.dto.SignupUserResponseDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.in.user.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseVo<SignupUserResponseDto>> signup(@Valid @RequestBody SignupUserRequestDto signupUserRequestDto) {
        SignupUserCommand signupUserCommand = SignupUserCommand.fromSignupUserRequestDto(signupUserRequestDto);
        User user = userUseCase.signup(signupUserCommand);
        return ResponseEntity.ok(
                ApiResponseVo.successWithData(
                        SuccessCodeEnum.SIGNUP_SUCCESS,
                        SignupUserResponseDto.fromUserDomain(user)
                )
        );
    }
}