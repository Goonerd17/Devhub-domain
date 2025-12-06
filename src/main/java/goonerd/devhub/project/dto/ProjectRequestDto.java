package goonerd.devhub.project.dto;

import goonerd.devhub.project.entity.Project;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {

    @NotBlank
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

    public Project toEntity() {
        return Project.builder()
                .username(this.username)
                .description(this.description)
                .recruitCount(this.recruitCount)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
