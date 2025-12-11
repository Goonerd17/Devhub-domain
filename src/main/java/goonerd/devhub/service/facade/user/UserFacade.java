package goonerd.devhub.service.facade.user;

import goonerd.devhub.adapters.in.user.dto.SignupUserCommand;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.ports.in.UserUseCase;
import goonerd.devhub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade implements UserUseCase {

    private final UserService userService;

    public ApiResponseVo<?> signup(SignupUserCommand signupUserCommand) {
        return ApiResponseVo.successWithParamAndData(SuccessCodeEnum.SIGNUP_SUCCESS, signupUserCommand, userService.signup(signupUserCommand));
    }
}