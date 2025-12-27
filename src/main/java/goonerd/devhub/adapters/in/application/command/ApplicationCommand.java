package goonerd.devhub.adapters.in.application.command;

import goonerd.devhub.adapters.in.application.dto.ApplicationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApplicationCommand {

    private String projectGuid;
    private String applicationGuid;
    private String applicantGuid;
    private String applicantEmail;
    private String applicantName;
    private String reviewerGuid;
    private String motivation;
    private String position;
    private String skillLevel;

    public static ApplicationCommand fromApplyApplicationRequestDto(String projectGuid, ApplicationRequestDto applicationRequestDto, String userGuid) {
        return ApplicationCommand.builder()
                .projectGuid(projectGuid)
                .applicantGuid(userGuid)
                .applicantEmail(applicationRequestDto.getApplicantEmail())
                .applicantName(applicationRequestDto.getApplicantName())
                .motivation(applicationRequestDto.getMotivation())
                .position(applicationRequestDto.getPosition())
                .skillLevel(applicationRequestDto.getSkillLevel())
                .build();
    }

    public static ApplicationCommand fromApproveApplicationRequestDto(String projectGuid, String applicationGuid, String userGuid) {
        return ApplicationCommand.builder()
                .projectGuid(projectGuid)
                .applicationGuid(applicationGuid)
                .reviewerGuid(userGuid)
                .build();
    }
}
