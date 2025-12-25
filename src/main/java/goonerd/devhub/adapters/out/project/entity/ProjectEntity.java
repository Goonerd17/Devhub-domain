package goonerd.devhub.adapters.out.project.entity;

import goonerd.devhub.adapters.out.common.BaseEntity;
import goonerd.devhub.domain.project.ProjectProgressType;
import goonerd.devhub.domain.project.RecruitmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity extends BaseEntity {

    @Id @Column(length = 36, nullable = false, unique = true)
    private String projectGuid;

    private String authorId;
    private String authorName;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private RecruitmentType recruitmentType;

    @Enumerated(EnumType.STRING)
    private ProjectProgressType projectProgressType;

    private int recruitCount;
    private int likes;
    private LocalDate startDate;
    private LocalDate endDate;

    @BatchSize(size = 20)
    @ElementCollection
    @CollectionTable(
            name = "project_skills",
            joinColumns = @JoinColumn(name = "project_guid")
    )
    @Column(name = "skill")
    private List<String> projectSkillList;

    @PrePersist
    public void prePersist() {
        if (this.projectGuid == null) {
            this.projectGuid = UUID.randomUUID().toString().replace("-", "");
        }
    }
}