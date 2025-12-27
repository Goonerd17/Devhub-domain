package goonerd.devhub.adapters.out.position.entity;

import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "position")
public class PositionEntity {

    @Id @Column(length = 36, nullable = false, unique = true)
    private String positionGuid;

    @Column(nullable = false)
    private String projectId;

    @Column(nullable = false)
    private String positionName;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String level;

    private int approvedCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "project_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_position_project")
    )
    private ProjectEntity projectEntity;
}