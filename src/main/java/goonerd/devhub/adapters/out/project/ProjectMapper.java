package goonerd.devhub.adapters.out.project;

import goonerd.devhub.adapters.out.position.entity.PositionEntity;
import goonerd.devhub.adapters.out.position.PositionMapper;
import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import goonerd.devhub.domains.common.AuditInfo;
import goonerd.devhub.domains.position.Position;
import goonerd.devhub.domains.project.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project project) {
        return ProjectEntity.builder()
                .projectGuid(project.getProjectGuid())
                .authorGuid(project.getAuthorGuid())
                .authorName(project.getAuthorName())
                .title(project.getTitle())
                .content(project.getContent())
                .recruitmentType(project.getRecruitmentType())
                .progressType(project.getProgressType())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .skillList(new ArrayList<>(project.getSkillList()))
                .recruitCount(project.getRecruitCount())
                .likes(project.getLikes())
                .build();
    }

    public static Project toDomain(ProjectEntity projectEntity, List<PositionEntity> positionEntityList) {
        List<Position> positionList = positionEntityList
                .stream()
                .map(PositionMapper::toDomain)
                .toList();
        return Project.reconstruct(
                projectEntity.getProjectGuid(),
                projectEntity.getAuthorGuid(),
                projectEntity.getAuthorName(),
                projectEntity.getTitle(),
                projectEntity.getContent(),
                projectEntity.getRecruitmentType(),
                projectEntity.getProgressType(),
                projectEntity.getStartDate(),
                projectEntity.getEndDate(),
                positionList,
                projectEntity.getSkillList(),
                projectEntity.getRecruitCount(),
                projectEntity.getLikes(),
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