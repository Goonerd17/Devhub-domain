package goonerd.devhub.domain.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PositionSlot {

    private final String position;
    private final String proficiency;
    private final int capacity;
    private List<String> acceptedUserIds;

    public PositionSlot(String position, String proficiency, int capacity, List<String> acceptedUserIds) {
        this.position = Objects.requireNonNull(position);
        this.proficiency = Objects.requireNonNull(proficiency);
        this.capacity = capacity;
        this.acceptedUserIds = acceptedUserIds != null ? new ArrayList<>(acceptedUserIds) : new ArrayList<>();
    }

    public void acceptUser(String userId) {
        if (acceptedUserIds.size() >= capacity) {
            throw new IllegalStateException("모집 인원을 초과했습니다.");
        }
        acceptedUserIds.add(userId);
    }

    public boolean isFull() {
        return acceptedUserIds.size() >= capacity;
    }

    public String getPosition() { return position; }
    public String getProficiency() { return proficiency; }
    public int getCapacity() { return capacity; }
    public List<String> getAcceptedUserIds() { return List.copyOf(acceptedUserIds); }
}
