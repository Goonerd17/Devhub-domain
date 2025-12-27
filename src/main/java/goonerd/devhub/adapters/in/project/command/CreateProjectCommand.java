package goonerd.devhub.adapters.in.project.command;

import goonerd.devhub.adapters.in.project.dto.CreateProjectRequestDto;
import goonerd.devhub.domain.project.ProgressType;
import goonerd.devhub.domain.project.RecruitmentType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CreateProjectCommand {

    private String authorGuid;
    private String authorName;
    private String title;
    private String content;
    private RecruitmentType recruitmentType;
    private ProgressType progressType;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CreateProjectPositionCommand> positions;
    private List<String> skills;
    private int recruitCount;

    public static CreateProjectCommand fromCreateProjectRequestDto(CreateProjectRequestDto createProjectRequestDto, String userGuid) {
        return CreateProjectCommand.builder()
                .authorGuid(userGuid)
                .authorName(createProjectRequestDto.getAuthorName())
                .title(createProjectRequestDto.getTitle())
                .content(createProjectRequestDto.getContent())
                .recruitmentType(parseRecruitmentType(createProjectRequestDto.getRecruitmentType()))
                .progressType(parseProgressType(createProjectRequestDto.getProgressType()))
                .startDate(createProjectRequestDto.getStartDate())
                .endDate(createProjectRequestDto.getEndDate())
                .positions(
                        createProjectRequestDto.getPositionRequestDtoList().stream()
                                .map(CreateProjectPositionCommand::fromCreateProjectRequestDto)
                                .toList()
                )
                .skills(createProjectRequestDto.getSkills())
                .build();
    }

    private static RecruitmentType parseRecruitmentType(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("모집 유형은 필수입니다.");
        }
        try {
            return RecruitmentType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 모집 유형입니다.");
        }
    }

    private static ProgressType parseProgressType(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("프로젝트 진행 방식은 필수입니다.");
        }
        return ProgressType.from(value);
    }
}