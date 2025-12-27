package goonerd.devhub.adapters.out.application.entity;

import goonerd.devhub.adapters.out.common.BaseEntity;
import goonerd.devhub.domain.application.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applications")
public class ApplicationEntity extends BaseEntity {

    @Id @Column(name = "application_guid", length = 36)
    private String applicationGuid;

    @Column(name = "project_guid", nullable = false)
    private String projectGuid;

    @Column(name = "applicant_guid", nullable = false)
    private String applicantGuid;

    @Column(name = "applicant_Email", nullable = false)
    private String applicantEmail;

    @Column(name = "applicant_Name", nullable = false)
    private String applicantName;

    @Column(name = "motivation", columnDefinition = "TEXT")
    private String motivation;

    @Column(name = "position", nullable = false)
    private String positionName;

    @Column(name = "skill_level", nullable = false)
    private String skillLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus applicationStatus;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
}