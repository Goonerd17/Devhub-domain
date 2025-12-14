package goonerd.devhub.adapters.in.application.command;

import goonerd.devhub.adapters.in.application.dto.ApplyApplicationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApplyApplicationCommand {

    private String projectGuid;
    private String userId;
    private String applicantName;
    private String applicantEmail;
    private String motivation;
    private String position;
    private String skillLevel;

    public static ApplyApplicationCommand fromApplyApplicationRequestDto(String projectGuid, ApplyApplicationRequestDto applyApplicationRequestDto, String userId) {
        return ApplyApplicationCommand.builder()
                .projectGuid(projectGuid)
                .userId(userId)
                .applicantName(applyApplicationRequestDto.getApplicantName())
                .applicantEmail(userId)
                .motivation(applyApplicationRequestDto.getMotivation())
                .position(applyApplicationRequestDto.getPosition())
                .skillLevel(applyApplicationRequestDto.getSkillLevel())
                .build();
    }
}
