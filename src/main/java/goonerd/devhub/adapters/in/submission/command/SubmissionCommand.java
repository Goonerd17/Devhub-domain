package goonerd.devhub.adapters.in.submission.command;

import goonerd.devhub.adapters.in.submission.dto.SubmissionRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubmissionCommand {

    private String projectGuid;
    private String submitterId;
    private String submitterName;
    private String submitterEmail;
    private String motivation;
    private String position;
    private String skillLevel;

    public static SubmissionCommand fromApplyApplicationRequestDto(String projectGuid, SubmissionRequestDto submissionRequestDto, String userId) {
        return SubmissionCommand.builder()
                .projectGuid(projectGuid)
                .submitterId(userId)
                .submitterName(submissionRequestDto.getSubmitterName())
                .submitterEmail(userId)
                .motivation(submissionRequestDto.getMotivation())
                .position(submissionRequestDto.getPosition())
                .skillLevel(submissionRequestDto.getSkillLevel())
                .build();
    }
}
