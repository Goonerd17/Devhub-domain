package goonerd.devhub.adapters.in.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginUserRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
