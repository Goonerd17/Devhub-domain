package goonerd.devhub.adapters.in.application.dto;

import goonerd.devhub.domain.application.Application;

import java.time.LocalDateTime;

public class ApplyApplicationResponseDto {

    private String applicationGuid;
    private String projectGuid;
    private String userId;
    private String username;
    private String position;
    private String proficiency;
    private String status;
    private LocalDateTime appliedAt;

    // 생성자
    public ApplyApplicationResponseDto(String applicationGuid, String projectGuid,
                                       String userId, String username,
                                       String position, String proficiency,
                                       String status, LocalDateTime appliedAt) {
        this.applicationGuid = applicationGuid;
        this.projectGuid = projectGuid;
        this.userId = userId;
        this.username = username;
        this.position = position;
        this.proficiency = proficiency;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    // Domain → DTO 변환
    public static ApplyApplicationResponseDto fromDomain(Application application) {
        return new ApplyApplicationResponseDto(
                application.getApplicationGuid(),
                application.getProjectGuid(),
                application.getUserId(),
                application.getUsername(),
                application.getRequirement().position(),
                application.getRequirement().skillLevel().name(),
                application.getStatus().name(), // Enum → String
                application.getAppliedAt()
        );
    }

    // getter
    public String getApplicationGuid() { return applicationGuid; }
    public String getProjectGuid() { return projectGuid; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPosition() { return position; }
    public String getProficiency() { return proficiency; }
    public String getStatus() { return status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
}
