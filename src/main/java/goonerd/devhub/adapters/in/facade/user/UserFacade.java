package goonerd.devhub.adapters.in.facade.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.adapters.in.user.dto.SignupUserResponseDto;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserUseCase userUseCase;

    public SignupUserResponseDto signup(SignupUserCommand signupUserCommand) {
        User user = userUseCase.signup(signupUserCommand);
        return SignupUserResponseDto.fromUserDomain(user);
    }
}