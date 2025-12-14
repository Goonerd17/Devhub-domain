package goonerd.devhub.adapters.out.project;

import goonerd.devhub.domain.common.AuditInfo;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.domain.project.Project;

import java.util.List;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project) {
        return ProjectEntity.builder()
                .authorId(project.getAuthorId())
                .authorName(project.getAuthorName())
                .title(project.getTitle())
                .description(project.getDescription())
                .recruitmentType(project.getRecruitmentType())
                .status(project.getStatus())
                .projectProgressType(project.getDeliveryType())
                .recruitCount(project.getRecruitCount())
                .likes(project.getLikes())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .skills(project.getSkills())
                .build();
    }

    public static Project toDomain(ProjectEntity projectEntity, List<PositionSlotEntity> positionSlotEntities) {
        List<PositionSlot> positionSlots = positionSlotEntities
                .stream()
                .map(PositionSlotMapper::toDomain)
                .toList();
        return Project.reconstruct(
                projectEntity.getProjectGuid(),
                projectEntity.getAuthorId(),
                projectEntity.getAuthorName(),
                projectEntity.getTitle(),
                projectEntity.getDescription(),
                projectEntity.getRecruitmentType(),
                projectEntity.getStatus(),
                projectEntity.getProjectProgressType(),
                projectEntity.getRecruitCount(),
                projectEntity.getLikes(),
                projectEntity.getStartDate(),
                projectEntity.getEndDate(),
                positionSlots,
                projectEntity.getSkills(),
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