package goonerd.devhub.adapters.in.project.command;

import goonerd.devhub.adapters.in.project.dto.SearchProjectRequestDto;
import goonerd.devhub.domain.project.ProjectProgressType;
import goonerd.devhub.domain.project.ProjectStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SearchProjectCommand {

    private String keyword;
    private ProjectStatus projectStatus;
    private ProjectProgressType projectProgressType;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
    private LocalDate endDateFrom;
    private LocalDate endDateTo;

    public static SearchProjectCommand fromProjectSearchRequestDto(SearchProjectRequestDto searchProjectRequestDto) {
        return SearchProjectCommand.builder()
                .keyword(searchProjectRequestDto.getKeyword())
                .projectStatus(parseStatus(searchProjectRequestDto.getProjectStatus()))
                .projectProgressType(parseProgressType(searchProjectRequestDto.getProjectProgressType()))
                .startDateFrom(searchProjectRequestDto.getStartDateFrom())
                .startDateTo(searchProjectRequestDto.getStartDateTo())
                .endDateFrom(searchProjectRequestDto.getEndDateFrom())
                .endDateTo(searchProjectRequestDto.getEndDateTo())
                .build();
    }

    private static ProjectStatus parseStatus(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return ProjectStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 프로젝트 상태입니다.");
        }
    }

    private static ProjectProgressType parseProgressType(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return ProjectProgressType.from(value);
    }

}
