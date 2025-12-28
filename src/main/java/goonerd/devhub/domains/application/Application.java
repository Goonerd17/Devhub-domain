package goonerd.devhub.domains.application;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domains.common.AuditInfo;

import java.time.LocalDateTime;

public class Application {

    private final String applicationGuid;
    private final String projectGuid;
    private final String applicantGuid;

    private String applicantEmail;
    private String applicantName;

    private String motivation;
    private String positionName;
    private String skillLevel;

    private ApplicationStatus applicationStatus;
    private LocalDateTime appliedAt;
    private AuditInfo auditInfo;

    private Application(
            String applicationGuid,
            String projectGuid,
            String applicantGuid,
            String applicantEmail,
            String applicantName,
            String motivation,
            String positionName,
            String skillLevel,
            ApplicationStatus applicationStatus,
            LocalDateTime appliedAt,
            AuditInfo auditInfo
    ) {
        if (projectGuid == null || projectGuid.isBlank())
            throw DomainRuleException.of(ErrorCodeEnum.APPLICATION_PROJECT_FAIL);

        this.applicationGuid = applicationGuid;
        this.projectGuid = projectGuid;
        this.applicantGuid = applicantGuid;
        this.applicantEmail = applicantEmail;
        this.applicantName = applicantName;
        this.motivation = motivation;
        this.positionName = positionName;
        this.skillLevel = skillLevel;
        this.applicationStatus = applicationStatus;

        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static Application createApplication(
            String applicationGuid,
            String projectGuid,
            String applicantGuid,
            String applicantEmail,
            String applicantName,
            String motivation,
            String position,
            String skillLevel
    ) {
        return new Application(
                applicationGuid,
                projectGuid,
                applicantGuid,
                applicantEmail,
                applicantName,
                motivation,
                position,
                skillLevel,
                ApplicationStatus.PENDING,
                LocalDateTime.now(),
                AuditInfo.empty()
        );
    }

    public static Application reconstruct(
            String applicationGuid,
            String projectGuid,
            String applicantGuid,
            String applicantEmail,
            String applicantName,
            String motivation,
            String position,
            String skillLevel,
            ApplicationStatus status,
            LocalDateTime appliedAt,
            AuditInfo auditInfo
    ) {
        return new Application(
                applicationGuid,
                projectGuid,
                applicantGuid,
                applicantEmail,
                applicantName,
                motivation,
                position,
                skillLevel,
                status,
                appliedAt,
                auditInfo
        );
    }

    public void approve() {
        if (this.applicationStatus != ApplicationStatus.PENDING)
            throw DomainRuleException.of(ErrorCodeEnum.APPLICATION_APPROVE_FAIL);
        this.applicationStatus = ApplicationStatus.ACCEPTED;
    }

    public void reject() {
        if (this.applicationStatus != ApplicationStatus.PENDING)
            throw DomainRuleException.of(ErrorCodeEnum.APPLICATION_REJECT_FAIL);
        this.applicationStatus = ApplicationStatus.REJECTED;
    }

    public void cancel() {
        if (this.applicationStatus == ApplicationStatus.ACCEPTED)
            throw DomainRuleException.of(ErrorCodeEnum.APPLICATION_CANCEL_FAIL);
        this.applicationStatus = ApplicationStatus.CANCELED;
    }

    public String getApplicationGuid() { return applicationGuid; }
    public String getProjectGuid() { return projectGuid; }
    public String getApplicantGuid() { return applicantGuid; }
    public String getApplicantEmail() { return applicantEmail; }
    public String getApplicantName() { return applicantName; }
    public String getMotivation() { return motivation; }
    public String getPositionName() {
        return positionName;
    }
    public String getSkillLevel() {
        return skillLevel;
    }
    public ApplicationStatus getApplicationStatus() { return applicationStatus; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}