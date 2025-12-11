package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.user.dto.SignupUserCommand;
import goonerd.devhub.common.vo.ApiResponseVo;

public interface UserUseCase {
    ApiResponseVo<?> signup(SignupUserCommand command);
}
