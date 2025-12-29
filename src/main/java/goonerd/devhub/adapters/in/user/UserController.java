package goonerd.devhub.adapters.in.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.adapters.in.user.dto.SignupUserRequestDto;
import goonerd.devhub.adapters.in.user.dto.SignupUserResponseDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.domains.user.User;
import goonerd.devhub.ports.in.user.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @PutMapping("/profile")
//    public ResponseEntity<ApiResponseVo<SignupUserResponseDto>> update(@Valid @RequestBody SignupUserRequestDto signupUserRequestDto) {
//        return ResponseEntity.ok(
//                ApiResponseVo.successWithData(
//                        SuccessCodeEnum.SIGNUP_SUCCESS,
//                        SignupUserResponseDto.fromUserDomain(user)
//                )
//        );
//    }
}