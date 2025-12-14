package goonerd.devhub.adapters.in.submission.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubmissionRequestDto {

    @NotBlank
    private String submitterName;

    @NotBlank
    private String submitterEmail;

    @NotBlank
    private String motivation;

    @NotBlank
    private String position;

    @NotBlank
    private String skillLevel;
}