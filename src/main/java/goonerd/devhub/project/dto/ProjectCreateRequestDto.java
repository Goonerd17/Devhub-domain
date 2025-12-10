package goonerd.devhub.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트 생성 요청 정보")
public class ProjectCreateRequestDto {

    @NotBlank
    @Schema(description = "사용자 ID")
    private String userId;
    @NotBlank
    @Schema(description = "사용자 이름")
    private String username;
    @NotNull
    @Schema(description = "프로젝트 제목")
    private String title;
    @NotNull
    @Schema(description = "프로젝트 설명")
    private String description;
    @Positive
    @Schema(description = "모집 인원")
    private int recruitCount;
    @NotNull
    @FutureOrPresent
    @Schema(description = "시작 날짜", example = "2025-01-10")
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent
    @Schema(description = "종료 날짜", example = "2025-01-20")
    private LocalDate endDate;
}