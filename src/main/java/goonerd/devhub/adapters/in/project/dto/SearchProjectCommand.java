package goonerd.devhub.adapters.in.project.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SearchProjectCommand {

    private String username;
    private String keyword;
    private Integer minRecruitCount;
    private Integer maxRecruitCount;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
    private LocalDate endDateFrom;
    private LocalDate endDateTo;

    public static SearchProjectCommand fromProjectSearchRequestDto(SearchProjectRequestDto searchProjectRequestDto) {
        return SearchProjectCommand.builder()
                .username(searchProjectRequestDto.getUsername())
                .keyword(searchProjectRequestDto.getKeyword())
                .minRecruitCount(searchProjectRequestDto.getMinRecruitCount())
                .maxRecruitCount(searchProjectRequestDto.getMaxRecruitCount())
                .startDateFrom(searchProjectRequestDto.getStartDateFrom())
                .startDateTo(searchProjectRequestDto.getStartDateTo())
                .endDateFrom(searchProjectRequestDto.getEndDateFrom())
                .endDateTo(searchProjectRequestDto.getEndDateTo())
                .build();
    }
}
