package goonerd.devhub.project.command;

import goonerd.devhub.project.dto.ProjectCreateRequestDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProjectCreateCommand {

    private String userId;
    private String username;
    private String title;
    private String description;
    private int recruitCount;
    private LocalDate startDate;
    private LocalDate endDate;

    public static ProjectCreateCommand fromProjectCreateRequestDto(ProjectCreateRequestDto projectCreateRequestDto) {
        return ProjectCreateCommand.builder()
                .userId(projectCreateRequestDto.getUserId())
                .username(projectCreateRequestDto.getUsername())
                .title(projectCreateRequestDto.getTitle())
                .description(projectCreateRequestDto.getDescription())
                .recruitCount(projectCreateRequestDto.getRecruitCount())
                .startDate(projectCreateRequestDto.getStartDate())
                .endDate(projectCreateRequestDto.getEndDate())
                .build();
    }
}