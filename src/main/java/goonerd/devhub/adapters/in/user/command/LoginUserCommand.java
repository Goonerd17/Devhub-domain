package goonerd.devhub.adapters.in.user.command;

import goonerd.devhub.adapters.in.user.dto.LoginUserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginUserCommand {

    private final String userId;
    private final String password;

    public static LoginUserCommand fromLoginUserRequestDto(LoginUserRequestDto loginUserRequestDto) {
        return LoginUserCommand.builder()
                .userId(loginUserRequestDto.getUserId())
                .password(loginUserRequestDto.getPassword())
                .build();
    }
}