package goonerd.devhub.domain.application;

import goonerd.devhub.domain.common.AuditInfo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Application {

    private String applicationGuid;
    private String projectGuid;
    private String userId;

    private String username;
    private String motivation;
    private PositionRequirement positionRequirement;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
    private AuditInfo auditInfo;

    private Application(
            String applicationGuid,
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement positionRequirement,
            ApplicationStatus status,
            LocalDateTime appliedAt,
            AuditInfo auditInfo
    ) {
        this.applicationGuid = applicationGuid;

        if (projectGuid == null || projectGuid.isBlank())
            throw new IllegalArgumentException("projectGuid는 필수입니다.");
        this.projectGuid = projectGuid;

        if (userId == null || userId.isBlank())
            throw new IllegalArgumentException("userId는 필수입니다.");
        this.userId = userId;
        this.username = Objects.requireNonNull(username);
        this.motivation = Objects.requireNonNull(motivation);

        this.positionRequirement = Objects.requireNonNull(positionRequirement);

        this.status = Objects.requireNonNull(status);

        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static Application createApplication(
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement positionRequirement
    ) {
        return new Application(
                null,
                projectGuid,
                userId,
                username,
                motivation,
                positionRequirement,
                ApplicationStatus.PENDING,
                LocalDateTime.now(),
                AuditInfo.empty()
        );
    }

    public static Application reconstruct(
            String applicationGuid,
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement positionRequirement,
            ApplicationStatus status,
            LocalDateTime appliedAt,
            AuditInfo auditInfo
    ) {
        return new Application(
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
        if (this.status != ApplicationStatus.PENDING)
            throw new IllegalStateException("승인할 수 없는 상태입니다.");
        this.status = ApplicationStatus.ACCEPTED;
    }

    public void reject() {
        if (this.status != ApplicationStatus.PENDING)
            throw new IllegalStateException("거절할 수 없는 상태입니다.");
        this.status = ApplicationStatus.REJECTED;
    }

    public void cancel() {
        if (this.status == ApplicationStatus.ACCEPTED)
            throw new IllegalStateException("승인된 지원은 취소할 수 없습니다.");
        this.status = ApplicationStatus.CANCELED;
    }

    public String getApplicationGuid() { return applicationGuid; }
    public String getProjectGuid() { return projectGuid; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getMotivation() { return motivation; }
    public PositionRequirement getPositionRequirement() { return positionRequirement; }
    public ApplicationStatus getStatus() { return status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}