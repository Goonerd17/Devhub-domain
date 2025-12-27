package goonerd.devhub.adapters.in.user.command;

import goonerd.devhub.adapters.in.user.dto.LoginUserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginUserCommand {

    private final String email;
    private final String password;

    public static LoginUserCommand fromLoginUserRequestDto(LoginUserRequestDto loginUserRequestDto) {
        return LoginUserCommand.builder()
                .email(loginUserRequestDto.getEmail())
                .password(loginUserRequestDto.getPassword())
                .build();
    }
}