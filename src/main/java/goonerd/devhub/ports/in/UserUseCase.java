package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.domain.user.User;

public interface UserUseCase {
    User signup(SignupUserCommand command);
}