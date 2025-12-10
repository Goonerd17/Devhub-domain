package goonerd.devhub.project.command;

import goonerd.devhub.project.dto.ProjectSearchRequestDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProjectSearchCommand {

    private String username;
    private String keyword;
    private Integer minRecruitCount;
    private Integer maxRecruitCount;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
    private LocalDate endDateFrom;
    private LocalDate endDateTo;

    public static ProjectSearchCommand fromProjectSearchRequestDto(ProjectSearchRequestDto projectSearchRequestDto) {
        return ProjectSearchCommand.builder()
                .username(projectSearchRequestDto.getUsername())
                .keyword(projectSearchRequestDto.getKeyword())
                .minRecruitCount(projectSearchRequestDto.getMinRecruitCount())
                .maxRecruitCount(projectSearchRequestDto.getMaxRecruitCount())
                .startDateFrom(projectSearchRequestDto.getStartDateFrom())
                .startDateTo(projectSearchRequestDto.getStartDateTo())
                .endDateFrom(projectSearchRequestDto.getEndDateFrom())
                .endDateTo(projectSearchRequestDto.getEndDateTo())
                .build();
    }
}
