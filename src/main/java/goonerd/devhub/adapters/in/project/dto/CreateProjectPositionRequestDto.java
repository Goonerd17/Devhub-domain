package goonerd.devhub.adapters.in.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "모집 포지션 정보")
public class CreateProjectPositionRequestDto {

    @NotBlank
    @Schema(description = "포지션명", example = "BACKEND")
    private String position;

    @NotBlank
    @Schema(description = "숙련도", example = "JUNIOR")
    private String proficiency;

    @Positive
    @Schema(description = "모집 인원", example = "2")
    private int capacity;

    private String skillLevel;
}