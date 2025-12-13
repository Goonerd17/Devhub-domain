package goonerd.devhub.adapters.in.application.command;

import goonerd.devhub.adapters.in.application.dto.ApplyApplicationRequestDto;
import goonerd.devhub.domain.application.PositionRequirement;
import goonerd.devhub.domain.common.SkillLevel;
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
    private PositionRequirement requirement;

    public static ApplyApplicationCommand fromApplyApplicationRequestDto(String projectGuid, String userId, ApplyApplicationRequestDto applyApplicationRequestDto) {
        return ApplyApplicationCommand.builder()
                .projectGuid(projectGuid)
                .userId(userId)
                .applicantName(applyApplicationRequestDto.getApplicantName())
                .applicantEmail(userId)
                .motivation(applyApplicationRequestDto.getMotivation())
                .requirement(applyApplicationRequestDto.getRequirement())
                .build();
    }
}
