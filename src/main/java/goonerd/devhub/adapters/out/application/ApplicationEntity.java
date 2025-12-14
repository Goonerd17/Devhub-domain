package goonerd.devhub.adapters.out.application;

import goonerd.devhub.common.entity.BaseEntity;
import goonerd.devhub.domain.application.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applications")
public class ApplicationEntity extends BaseEntity {

    @Id @GeneratedValue
    @UuidGenerator
    @Column(name = "application_guid", length = 36)
    private String applicationGuid;

    @Column(name = "project_guid", nullable = false)
    private String projectGuid;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "motivation", columnDefinition = "TEXT")
    private String motivation;

    @Embedded
    private PositionRequirementEmbeddable requirement;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @Column(name = "applied_at")
    private LocalDateTime appliedAt;
}
