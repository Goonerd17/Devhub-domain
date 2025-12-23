package goonerd.devhub.domain.project;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;

import java.util.Objects;

public class PositionSlot {

    private String projectGuid;
    private final String position;
    private final int capacity;
    private final String level;
    private int approvedCount;

    public PositionSlot(String position, int capacity, String level, int approvedCount) {
        if (capacity <= 0) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_POSITION_RECRUITMENT_FAIL);
        }
        this.position = Objects.requireNonNull(position);
        this.capacity = capacity;
        this.level = Objects.requireNonNull(level);
        this.approvedCount = approvedCount;
    }

    public static PositionSlot reconstruct(
            String position,
            int capacity,
            String level,
            int approvedCount
    ) {
        return new PositionSlot(
                position,
                capacity,
                level,
                approvedCount
        );
    }

    public static PositionSlot createPositionSlot(
            String position,
            int capacity,
            String level
    ) {
        return new PositionSlot(position, capacity, level,0);
    }

    public boolean isFull() {
        return approvedCount >= capacity;
    }

    public void ensureCanApprove() {
        if (approvedCount >= capacity) {
            throw DomainRuleException.of(ErrorCodeEnum.UNKNOWN_FAIL);
        }
    }

    public void increaseApprovedCount() {
        this.approvedCount++;
    }

    public String getProjectGuid() { return projectGuid; }
    public String getPosition() { return position; }
    public int getCapacity() { return capacity; }
    public String getLevel() { return level; }
    public int getApprovedCount() { return approvedCount; }
    public void assignProject(String projectGuid) { this.projectGuid = projectGuid;}
}