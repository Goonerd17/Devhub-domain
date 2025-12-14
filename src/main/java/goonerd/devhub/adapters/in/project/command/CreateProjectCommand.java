package goonerd.devhub.adapters.in.project.command;

import goonerd.devhub.adapters.in.project.dto.CreateProjectRequestDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CreateProjectCommand {

    private String authorId;
    private String authorName;
    private String title;
    private String description;
    private String recruitmentType;
    private String projectProgressType;
    private int recruitCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CreateProjectPositionCommand> positions;
    private List<String> skills;

    public static CreateProjectCommand fromCreateProjectRequestDto(CreateProjectRequestDto createProjectRequestDto, String userId) {
        return CreateProjectCommand.builder()
                .authorId(userId)
                .authorName(createProjectRequestDto.getAuthorName())
                .title(createProjectRequestDto.getTitle())
                .description(createProjectRequestDto.getDescription())
                .recruitmentType(createProjectRequestDto.getRecruitmentType())
                .projectProgressType(createProjectRequestDto.getProjectProgressType())
                .startDate(createProjectRequestDto.getStartDate())
                .endDate(createProjectRequestDto.getEndDate())
                .positions(
                        createProjectRequestDto.getPositions().stream()
                                .map(CreateProjectPositionCommand::fromCreateProjectRequestDto)
                                .toList()
                )
                .skills(createProjectRequestDto.getSkills())
                .build();
    }
}