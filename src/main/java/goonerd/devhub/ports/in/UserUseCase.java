package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.user.dto.SignupUserCommand;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.domain.user.User;

public interface UserUseCase {

    ApiResponseVo<User> signup(SignupUserCommand command);
}
