package goonerd.devhub.project.dto;

import goonerd.devhub.common.enums.RegexPatternEnum;
import goonerd.devhub.common.validation.RegexMatch;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {

    @NotBlank
    @RegexMatch(RegexPatternEnum.USERNAME)
    private String username;
    @NotNull
    private String description;
    @Positive
    private int recruitCount;
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent
    private LocalDate endDate;
}