package goonerd.devhub.adapters.out.position.entity;

import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_position_slots")
public class PositionSlotEntity {

    @Id @Column(length = 36, nullable = false, unique = true)
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
    private int capacity;

    @Column(nullable = false)
    private String level;

    private int approvedCount;

    @PrePersist
    public void prePersist() {
        if (this.positionSlotGuid == null) {
            this.positionSlotGuid = UUID.randomUUID().toString().replace("-", "");
        }
    }
}
