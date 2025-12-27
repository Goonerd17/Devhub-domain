package goonerd.devhub.domain.position;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;

public class Position {

    private final String positionGuid;
    private final String projectGuid;
    private String positionName;
    private int capacity;
    private String level;
    private int approvedCount;

    public Position(String positionGuid, String projectGuid, String positionName, int capacity, String level, int approvedCount) {
        if (capacity <= 0) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_POSITION_RECRUITMENT_FAIL);
        }

        this.positionGuid = positionGuid;
        this.projectGuid = projectGuid;
        this.positionName = positionName;
        this.capacity = capacity;
        this.level = level;
        this.approvedCount = approvedCount;
    }

    public static Position reconstruct(
            String positionGuid,
            String projectGuid,
            String positionName,
            int capacity,
            String level,
            int approvedCount
    ) {
        return new Position(
                positionGuid,
                projectGuid,
                positionName,
                capacity,
                level,
                approvedCount
        );
    }

    public static Position createPosition(String positionGuid, String projectGuid, String positionName, int capacity, String level) {
        return new Position(positionGuid, projectGuid, positionName, capacity, level,0);
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

    public String getPositionGuid() { return positionGuid; }
    public String getProjectGuid() { return projectGuid; }
    public String getPositionName() { return positionName; }
    public int getCapacity() { return capacity; }
    public String getLevel() { return level; }
    public int getApprovedCount() { return approvedCount; }
}