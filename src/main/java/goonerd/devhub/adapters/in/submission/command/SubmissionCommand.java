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
    private String submissionGuid;
    private String submitterId;
    private String reviewerId;
    private String submitterName;
    private String submitterEmail;
    private String motivation;
    private String position;
    private String skillLevel;

    public static SubmissionCommand fromApplySubmissionRequestDto(String projectGuid, SubmissionRequestDto submissionRequestDto, String userId) {
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

    public static SubmissionCommand fromApproveSubmissionRequestDto(String projectGuid, String submissionGuid, String userId) {
        return SubmissionCommand.builder()
                .projectGuid(projectGuid)
                .submissionGuid(submissionGuid)
                .reviewerId(userId)
                .build();
    }
}
