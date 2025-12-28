package goonerd.devhub.adapters.out.project.entity;

import goonerd.devhub.adapters.out.common.BaseEntity;
import goonerd.devhub.domains.project.ProgressType;
import goonerd.devhub.domains.project.RecruitmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class ProjectEntity extends BaseEntity {

    @Id @Column(length = 36, nullable = false, unique = true)
    private String projectGuid;

    private String authorGuid;
    private String authorName;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private RecruitmentType recruitmentType;
    @Enumerated(EnumType.STRING)
    private ProgressType progressType;

    private LocalDate startDate;
    private LocalDate endDate;

    @BatchSize(size = 20)
    @ElementCollection
    @CollectionTable(
            name = "project_skills",
            joinColumns = @JoinColumn(name = "project_guid")
    )
    @Column(name = "skill")
    private List<String> skillList;

    private int recruitCount;
    private int likes;
}