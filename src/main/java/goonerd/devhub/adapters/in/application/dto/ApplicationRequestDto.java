package goonerd.devhub.adapters.in.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationRequestDto {

    @NotBlank
    private String applicantEmail;

    @NotBlank
    private String applicantName;

    @NotBlank
    private String motivation;

    @NotBlank
    private String positionName;

    @NotBlank
    private String level;
}