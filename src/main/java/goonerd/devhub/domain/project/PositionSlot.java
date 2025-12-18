package goonerd.devhub.domain.project;

import java.util.Objects;

public class PositionSlot {

    private String projectGuid;
    private final String position;
    private final String proficiency;
    private final int capacity;
    private int approvedCount;

    public PositionSlot(String position, String proficiency, int capacity, int approvedCount) {
        this.position = Objects.requireNonNull(position);
        this.proficiency = Objects.requireNonNull(proficiency);
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity는 1 이상이어야 합니다.");
        }
        this.capacity = capacity;
        this.approvedCount = approvedCount;
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

    public void acceptUser(String userId) {
        if (approvedCount >= capacity) {
            throw new IllegalStateException("모집 인원을 초과했습니다.");
        }
        approvedCount++;
    }

    public boolean isFull() {
        return approvedCount >= capacity;
    }
    public String getProjectGuid() { return projectGuid; }
    public String getPosition() { return position; }
    public String getProficiency() { return proficiency; }
    public int getCapacity() { return capacity; }
    public int getApprovedCount() { return approvedCount; }
    public void assignProject(String projectGuid) { this.projectGuid = projectGuid;}
}
