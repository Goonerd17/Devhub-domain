package goonerd.devhub.adapters.in.user;

import goonerd.devhub.adapters.in.facade.user.UserFacade;
import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.adapters.in.user.dto.SignupUserRequestDto;
import goonerd.devhub.adapters.in.user.dto.SignupUserResponseDto;
import goonerd.devhub.adapters.in.vo.ApiResponseVo;
import goonerd.devhub.common.enums.SuccessCodeEnum;
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

    private final UserFacade userFacade;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseVo<SignupUserResponseDto>> signup(@Valid @RequestBody SignupUserRequestDto signupUserRequestDto) {
        SignupUserCommand signupUserCommand = SignupUserCommand.fromSignupUserRequestDto(signupUserRequestDto);
        return ResponseEntity.ok(
                ApiResponseVo.successWithParamAndData(
                        SuccessCodeEnum.SIGNUP_SUCCESS,
                        signupUserCommand,
                        userFacade.signup(signupUserCommand)));
    }
}