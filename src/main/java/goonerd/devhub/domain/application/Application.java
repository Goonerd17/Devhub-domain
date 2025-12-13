package goonerd.devhub.domain.application;

import java.time.LocalDateTime;
import java.util.Objects;

public class Application {

    private final String applicationGuid;
    private final String projectGuid;
    private final String userId;

    private final String username;
    private final String motivation;
    private final PositionRequirement requirement;

    private ApplicationStatus status;

    private final LocalDateTime appliedAt;

    private Application(
            String applicationGuid,
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement requirement,
            ApplicationStatus status,
            LocalDateTime appliedAt
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
        this.requirement = Objects.requireNonNull(requirement);

        this.status = Objects.requireNonNull(status);
        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
    }

    public static Application createApplication(
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement requirement
    ) {
        return new Application(
                null,
                projectGuid,
                userId,
                username,
                motivation,
                requirement,
                ApplicationStatus.PENDING,
                LocalDateTime.now()
        );
    }

    public static Application reconstruct(
            String applicationGuid,
            String projectGuid,
            String userId,
            String username,
            String motivation,
            PositionRequirement requirement,
            ApplicationStatus status,
            LocalDateTime appliedAt
    ) {
        return new Application(
                applicationGuid,
                projectGuid,
                userId,
                username,
                motivation,
                requirement,
                status,
                appliedAt
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
    public PositionRequirement getRequirement() { return requirement; }
    public ApplicationStatus getStatus() { return status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
}