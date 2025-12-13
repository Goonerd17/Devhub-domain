package goonerd.devhub.service.facade.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.adapters.in.user.dto.SignupUserResponseDto;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.in.UserUseCase;
import goonerd.devhub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade implements UserUseCase {

    private final UserService userService;

    public SignupUserResponseDto signup(SignupUserCommand signupUserCommand) {
        User user = userService.signup(signupUserCommand);
        return SignupUserResponseDto.fromUserDomain(user);
    }
}