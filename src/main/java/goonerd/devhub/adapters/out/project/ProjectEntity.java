package goonerd.devhub.adapters.out.project;

import goonerd.devhub.common.entity.BaseEntity;
import goonerd.devhub.domain.project.DeliveryType;
import goonerd.devhub.domain.project.ProjectStatus;
import goonerd.devhub.domain.project.RecruitmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String projectGuid;
    private String userId;
    private String username;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private RecruitmentType recruitmentType;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    private int recruitCount;
    private String authorId; // UserId 참조
    private int likes;
    private LocalDate startDate;
    private LocalDate endDate;
    @ElementCollection
    private List<String> positions;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<String> acceptedUserIds;

}