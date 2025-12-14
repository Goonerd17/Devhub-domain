package goonerd.devhub.adapters.out.submission;

import goonerd.devhub.adapters.out.common.BaseEntity;
import goonerd.devhub.domain.submission.SubmissionStatus;
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
@Table(name = "submissions")
public class SubmissionEntity extends BaseEntity {

    @Id @GeneratedValue
    @UuidGenerator
    @Column(name = "submission_guid", length = 36)
    private String submissionGuid;

    @Column(name = "project_guid", nullable = false)
    private String projectGuid;

    @Column(name = "submitter_id", nullable = false)
    private String submitterId;

    @Column(name = "submitter_name", nullable = false)
    private String submitterName;

    @Column(name = "motivation", columnDefinition = "TEXT")
    private String motivation;

    @Embedded
    private PositionRequirementEmbeddable requirement;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SubmissionStatus submissionStatus;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
}
