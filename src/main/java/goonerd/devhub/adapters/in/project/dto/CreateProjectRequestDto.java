package goonerd.devhub.adapters.in.project.dto;

import goonerd.devhub.domain.project.ProjectProgressType;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.domain.project.RecruitmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트 생성 요청 정보")
public class CreateProjectRequestDto {

    @NotBlank
    @Schema(description = "프로젝트 작성자 사용자 ID")
    private String userId;

    @NotBlank
    @Schema(description = "프로젝트 작성자 사용자명")
    private String username;

    @NotNull
    @Schema(description = "프로젝트 제목")
    private String title;

    @NotNull
    @Schema(description = "프로젝트 내용")
    private String content;

    @NotNull
    @Schema(description = "모집 포지션 목록")
    private List<PositionSlot> positions;

    @NotNull
    @Schema(description = "필요 기술 목록")
    private List<String> skills;

    @NotNull
    @Schema(description = "모집 유형")
    private RecruitmentType recruitmentType;

    @NotNull
    @Schema(description = "진행 방식")
    private ProjectProgressType projectProgressType;

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
