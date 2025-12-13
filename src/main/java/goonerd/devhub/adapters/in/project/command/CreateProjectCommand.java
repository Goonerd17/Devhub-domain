package goonerd.devhub.adapters.in.project.command;

import goonerd.devhub.adapters.in.project.dto.CreateProjectRequestDto;
import goonerd.devhub.domain.project.ProjectProgressType;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.domain.project.RecruitmentType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CreateProjectCommand {

    private String userId;
    private String username;
    private String title;
    private String content;
    private RecruitmentType recruitmentType;
    private ProjectProgressType projectProgressType;
    private int recruitCount;
    private LocalDate startDate;
    private LocalDate endDate;
    List<PositionSlot> positions;
    private List<String> skills;

    public static CreateProjectCommand fromCreateProjectRequestDto(CreateProjectRequestDto createProjectRequestDto) {
        return CreateProjectCommand.builder()
                .userId(createProjectRequestDto.getUserId())
                .username(createProjectRequestDto.getUsername())
                .title(createProjectRequestDto.getTitle())
                .content(createProjectRequestDto.getContent())
                .recruitmentType(createProjectRequestDto.getRecruitmentType())
                .startDate(createProjectRequestDto.getStartDate())
                .endDate(createProjectRequestDto.getEndDate())
                .positions(createProjectRequestDto.getPositions())
                .skills(createProjectRequestDto.getSkills())
                .build();
    }
}