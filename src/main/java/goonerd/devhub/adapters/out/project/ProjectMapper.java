package goonerd.devhub.adapters.out.project;

import goonerd.devhub.domain.common.AuditInfo;
import goonerd.devhub.domain.project.Project;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project) {
        return ProjectEntity.builder()
                .userId(project.getUserId())
                .username(project.getUsername())
                .title(project.getTitle())
                .content(project.getContent())
                .recruitmentType(project.getRecruitmentType())
                .status(project.getStatus())
                .deliveryType(project.getDeliveryType())
                .recruitCount(project.getRecruitCount())
                .likes(project.getLikes())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .positions(project.getPositions())
                .skills(project.getSkills())
                .acceptedUserIds(project.getAcceptedUserIds())
                .build();
    }

    public static Project toDomain(ProjectEntity projectEntity) {
        return Project.reconstruct(
                projectEntity.getProjectGuid(),
                projectEntity.getUserId(),
                projectEntity.getUsername(),
                projectEntity.getTitle(),
                projectEntity.getContent(),
                projectEntity.getRecruitmentType(),
                projectEntity.getStatus(),
                projectEntity.getDeliveryType(),
                projectEntity.getRecruitCount(),
                projectEntity.getLikes(),
                projectEntity.getStartDate(),
                projectEntity.getEndDate(),
                projectEntity.getPositions(),
                projectEntity.getSkills(),
                projectEntity.getAcceptedUserIds(),
                toAuditInfo(projectEntity)
        );
    }

    private static AuditInfo toAuditInfo(ProjectEntity projectEntity) {
        return AuditInfo.of(
                projectEntity.getCreatedBy(),
                projectEntity.getCreatedAt(),
                projectEntity.getModifiedBy(),
                projectEntity.getModifiedAt()
        );
    }
}