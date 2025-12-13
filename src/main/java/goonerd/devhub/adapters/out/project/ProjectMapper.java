package goonerd.devhub.adapters.out.project;

import goonerd.devhub.domain.common.AuditInfo;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.domain.project.Project;

import java.util.List;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project) {
        List<PositionSlotEmbeddable> embeddables = project.getPositions().stream()
                .map(d -> new PositionSlotEmbeddable(
                        d.getPosition(),
                        d.getProficiency(),
                        d.getCapacity(),
                        d.getAcceptedUserIds()
                ))
                .toList();
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
                .positions(embeddables)
                .skills(project.getSkills())
                .build();
    }

    public static Project toDomain(ProjectEntity projectEntity) {

        List<PositionSlot> slots = projectEntity.getPositions().stream()
                .map(e -> new PositionSlot(
                        e.getPosition(),
                        e.getProficiency(),
                        e.getCapacity(),
                        e.getAcceptedUserIds()
                ))
                .toList();
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
                slots,
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