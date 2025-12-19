package goonerd.devhub.domain.submission;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.common.AuditInfo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Submission {

    private final String submissionGuid;
    private final String projectGuid;

    private final String submitterId;
    private String submitterName;

    private String motivation;
    private PositionRequirement positionRequirement;

    private SubmissionStatus submissionStatus;
    private LocalDateTime submittedAt;
    private AuditInfo auditInfo;

    private Submission(
            String submissionGuid,
            String projectGuid,
            String submitterId,
            String submitterName,
            String motivation,
            PositionRequirement positionRequirement,
            SubmissionStatus submissionStatus,
            LocalDateTime submittedAt,
            AuditInfo auditInfo
    ) {
        this.submissionGuid = submissionGuid;

        if (projectGuid == null || projectGuid.isBlank())
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_PROJECT_FAIL);
        this.projectGuid = projectGuid;

        if (submitterId == null || submitterId.isBlank())
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_USER_FAIL);
        this.submitterId = submitterId;
        this.submitterName = Objects.requireNonNull(submitterName);
        this.motivation = Objects.requireNonNull(motivation);

        this.positionRequirement = Objects.requireNonNull(positionRequirement);

        this.submissionStatus = Objects.requireNonNull(submissionStatus);

        this.submittedAt = submittedAt != null ? submittedAt : LocalDateTime.now();
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static Submission createApplication(
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement positionRequirement
    ) {
        return new Submission(
                null,
                projectGuid,
                userId,
                username,
                motivation,
                positionRequirement,
                SubmissionStatus.PENDING,
                LocalDateTime.now(),
                AuditInfo.empty()
        );
    }

    public static Submission reconstruct(
            String applicationGuid,
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement positionRequirement,
            SubmissionStatus status,
            LocalDateTime appliedAt,
            AuditInfo auditInfo
    ) {
        return new Submission(
                applicationGuid,
                projectGuid,
                userId,
                username,
                motivation,
                positionRequirement,
                status,
                appliedAt,
                auditInfo
        );
    }

    public void approve() {
        if (this.submissionStatus != SubmissionStatus.PENDING)
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_APPROVE_FAIL);
        this.submissionStatus = SubmissionStatus.ACCEPTED;
    }

    public void reject() {
        if (this.submissionStatus != SubmissionStatus.PENDING)
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_REJECT_FAIL);
        this.submissionStatus = SubmissionStatus.REJECTED;
    }

    public void cancel() {
        if (this.submissionStatus == SubmissionStatus.ACCEPTED)
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_CANCEL_FAIL);
        this.submissionStatus = SubmissionStatus.CANCELED;
    }

    public String getSubmissionGuid() { return submissionGuid; }
    public String getProjectGuid() { return projectGuid; }
    public String getSubmitterId() { return submitterId; }
    public String getSubmitterName() { return submitterName; }
    public String getMotivation() { return motivation; }
    public PositionRequirement getPositionRequirement() { return positionRequirement; }
    public SubmissionStatus getSubmissionStatus() { return submissionStatus; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}