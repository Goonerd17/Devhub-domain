package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.adapters.in.user.dto.SignupUserResponseDto;

public interface UserUseCase {
    SignupUserResponseDto signup(SignupUserCommand command);
}