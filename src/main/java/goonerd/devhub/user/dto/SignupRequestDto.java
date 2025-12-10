package goonerd.devhub.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank
    private String userId;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}