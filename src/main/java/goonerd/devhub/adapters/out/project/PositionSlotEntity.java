package goonerd.devhub.adapters.out.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "project_guid", nullable = false, length = 36)
    private String projectGuid;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String proficiency;

    @Column(nullable = false)
    private int capacity;

    @ElementCollection
    @CollectionTable(
            name = "project_position_accepted_users",
            joinColumns = @JoinColumn(name = "position_slot_id")
    )
    @Column(name = "user_id")
    private List<String> acceptedUserIds = new ArrayList<>();
}
