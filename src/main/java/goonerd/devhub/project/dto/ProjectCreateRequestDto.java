package goonerd.devhub.project.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProjectCreateRequestDto {

    @NotBlank
    private String userId;
    @NotBlank
    private String username;
    @NotNull
    private String title;
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