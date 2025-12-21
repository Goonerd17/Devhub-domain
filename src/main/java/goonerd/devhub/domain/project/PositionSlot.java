package goonerd.devhub.domain.project;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;

import java.util.Objects;

public class PositionSlot {

    private String projectGuid;
    private final String position;
    private final String proficiency;
    private final int capacity;
    private int approvedCount;

    public PositionSlot(String position, String proficiency, int capacity, int approvedCount) {
        if (capacity <= 0) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_POSITION_RECRUITMENT_FAIL);
        }
        this.position = Objects.requireNonNull(position);
        this.proficiency = Objects.requireNonNull(proficiency);
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
    public String getProficiency() { return proficiency; }
    public int getCapacity() { return capacity; }
    public int getApprovedCount() { return approvedCount; }
    public void assignProject(String projectGuid) { this.projectGuid = projectGuid;}
}