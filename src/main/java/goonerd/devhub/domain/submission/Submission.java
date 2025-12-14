package goonerd.devhub.domain.submission;

import goonerd.devhub.domain.common.AuditInfo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Submission {

    private String submissionGuid;
    private String projectGuid;

    private String submitterId;
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
            throw new IllegalArgumentException("projectGuid는 필수입니다.");
        this.projectGuid = projectGuid;

        if (submitterId == null || submitterId.isBlank())
            throw new IllegalArgumentException("userId는 필수입니다.");
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
            throw new IllegalStateException("승인할 수 없는 상태입니다.");
        this.submissionStatus = SubmissionStatus.ACCEPTED;
    }

    public void reject() {
        if (this.submissionStatus != SubmissionStatus.PENDING)
            throw new IllegalStateException("거절할 수 없는 상태입니다.");
        this.submissionStatus = SubmissionStatus.REJECTED;
    }

    public void cancel() {
        if (this.submissionStatus == SubmissionStatus.ACCEPTED)
            throw new IllegalStateException("승인된 지원은 취소할 수 없습니다.");
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