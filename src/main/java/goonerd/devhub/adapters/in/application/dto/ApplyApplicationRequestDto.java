package goonerd.devhub.adapters.in.application.dto;

import goonerd.devhub.domain.application.PositionRequirement;
import goonerd.devhub.domain.common.SkillLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplyApplicationRequestDto {

    @NotBlank
    private String applicantName;

    @NotBlank
    private String applicantEmail;

    @NotBlank
    private String motivation;

    @NotBlank
    private PositionRequirement requirement;
}
