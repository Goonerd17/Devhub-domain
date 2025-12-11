package goonerd.devhub.adapters.in.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginUserCommand {

    private final String userId;
    private final String password;

    public static LoginUserCommand createLoginUserCommand(LoginRequestDto loginRequestDto) {
        return LoginUserCommand.builder()
                .userId(loginRequestDto.getUserId())
                .password(loginRequestDto.getPassword())
                .build();
    }
}