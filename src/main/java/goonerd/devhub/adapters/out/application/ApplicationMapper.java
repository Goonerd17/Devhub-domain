package goonerd.devhub.adapters.out.application;

import goonerd.devhub.adapters.out.application.entity.ApplicationEntity;
import goonerd.devhub.domains.application.Application;
import goonerd.devhub.domains.common.AuditInfo;

public class ApplicationMapper {

    public static ApplicationEntity toEntity(Application application) {
        return ApplicationEntity.builder()
                .applicationGuid(application.getApplicationGuid())
                .projectGuid(application.getProjectGuid())
                .applicantGuid(application.getApplicantGuid())
                .applicantEmail(application.getApplicantEmail())
                .applicantName(application.getApplicantName())
                .motivation(application.getMotivation())
                .positionName(application.getPositionName())
                .skillLevel(application.getSkillLevel())
                .applicationStatus(application.getApplicationStatus())
                .submittedAt(application.getAppliedAt())
                .build();
    }

    public static Application toDomain(ApplicationEntity applicationEntity) {
        return Application.reconstruct(
                applicationEntity.getApplicationGuid(),
                applicationEntity.getProjectGuid(),
                applicationEntity.getApplicantGuid(),
                applicationEntity.getApplicantEmail(),
                applicationEntity.getApplicantName(),
                applicationEntity.getMotivation(),
                applicationEntity.getPositionName(),
                applicationEntity.getSkillLevel(),
                applicationEntity.getApplicationStatus(),
                applicationEntity.getSubmittedAt(),
                toAuditInfo(applicationEntity)
        );
    }

    private static AuditInfo toAuditInfo(ApplicationEntity applicationEntity) {
        return AuditInfo.of(applicationEntity.getCreatedBy(),
                applicationEntity.getCreatedAt(),
                applicationEntity.getModifiedBy(),
                applicationEntity.getModifiedAt()
        );
    }
}