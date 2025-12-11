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

    public static SignupUserCommand createSignupUserCommand(SignupRequestDto signupRequestDto) {
        return SignupUserCommand.builder()
                .userId(signupRequestDto.getUserId())
                .username(signupRequestDto.getUsername())
                .password(signupRequestDto.getPassword())
                .build();
    }
}