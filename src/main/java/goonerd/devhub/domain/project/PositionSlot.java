package goonerd.devhub.domain.project;

import java.util.Objects;

public class PositionSlot {

    private String projectGuid;
    private final String position;
    private final String proficiency;
    private final int capacity;

    public PositionSlot(String position, String proficiency, int capacity, int approvedCount) {
        this.position = Objects.requireNonNull(position);
        this.proficiency = Objects.requireNonNull(proficiency);
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity는 1 이상이어야 합니다.");
        }
        this.capacity = capacity;
    }

    public static PositionSlot reconstruct(
            String position,
            String proficiency,
            int capacity,
            int approvedCount
    ) {
        return new PositionSlot(
                position,
                proficiency,
                capacity,
                approvedCount
        );
    }

    public static PositionSlot createPositionSlot(
            String position,
            String proficiency,
            int capacity
    ) {
        return new PositionSlot(position, proficiency, capacity, 0);
    }

    public String getProjectGuid() { return projectGuid; }
    public String getPosition() { return position; }
    public String getProficiency() { return proficiency; }
    public int getCapacity() { return capacity; }
    public void assignProject(String projectGuid) { this.projectGuid = projectGuid;}
}