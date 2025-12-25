package goonerd.devhub.adapters.in.mail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmEmailVerificationRequestDto {

    @NotBlank
    @Size(min = 6, max = 6)
    private String code;
}