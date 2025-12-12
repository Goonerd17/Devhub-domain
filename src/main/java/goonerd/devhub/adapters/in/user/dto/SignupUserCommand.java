package goonerd.devhub.adapters.in.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignupUserCommand {

    private final String userId;
    private final String username;
    private final String password;

    public static SignupUserCommand fromSignupUserRequestDto(SignupUserRequestDto signupUserRequestDto) {
        return SignupUserCommand.builder()
                .userId(signupUserRequestDto.getUserId())
                .username(signupUserRequestDto.getUsername())
                .password(signupUserRequestDto.getPassword())
                .build();
    }
}