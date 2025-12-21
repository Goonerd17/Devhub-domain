package goonerd.devhub.adapters.out.project.position;

import goonerd.devhub.adapters.out.project.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_position_slots")
public class PositionSlotEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String positionSlotGuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "project_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_position_slot_project")
    )
    private ProjectEntity projectEntity;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String proficiency;

    @Column(nullable = false)
    private int capacity;

    private String skillLevel;

    private int approvedCount;
}
