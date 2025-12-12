package goonerd.devhub.adapters.in.project.dto;

import goonerd.devhub.domain.project.DeliveryType;
import goonerd.devhub.domain.project.RecruitmentType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CreateProjectCommand {

    private final String userId;
    private final String username;
    private final String title;
    private final String content;
    private final RecruitmentType recruitmentType;
    private final DeliveryType deliveryType;
    private final int recruitCount;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<String> positions;
    private final List<String> skills;

    private CreateProjectCommand(
            String userId,
            String username,
            String title,
            String content,
            RecruitmentType recruitmentType,
            DeliveryType deliveryType,
            int recruitCount,
            LocalDate startDate,
            LocalDate endDate,
            List<String> positions,
            List<String> skills
    ) {
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.recruitmentType = recruitmentType;
        this.deliveryType = deliveryType;
        this.recruitCount = recruitCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.positions = positions != null ? List.copyOf(positions) : List.of();
        this.skills = skills != null ? List.copyOf(skills) : List.of();
    }

    public static CreateProjectCommand fromCreateProjectRequestDto(CreateProjectRequestDto createProjectRequestDto) {
        return new CreateProjectCommand(
                createProjectRequestDto.getUserId(),
                createProjectRequestDto.getUsername(),
                createProjectRequestDto.getTitle(),
                createProjectRequestDto.getContent(),
                createProjectRequestDto.getRecruitmentType(),
                createProjectRequestDto.getDeliveryType(),
                createProjectRequestDto.getRecruitCount(),
                createProjectRequestDto.getStartDate(),
                createProjectRequestDto.getEndDate(),
                createProjectRequestDto.getPositions(),
                createProjectRequestDto.getSkills()
        );
    }
}