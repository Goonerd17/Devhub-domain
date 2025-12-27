package goonerd.devhub.adapters.in.user.command;

import goonerd.devhub.adapters.in.user.dto.SignupUserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignupUserCommand {

    private final String email;
    private final String username;
    private final String password;

    public static SignupUserCommand fromSignupUserRequestDto(SignupUserRequestDto signupUserRequestDto) {
        return SignupUserCommand.builder()
                .email(signupUserRequestDto.getEmail())
                .username(signupUserRequestDto.getUsername())
                .password(signupUserRequestDto.getPassword())
                .build();
    }
}