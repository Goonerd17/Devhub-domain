package goonerd.devhub.adapters.out.project;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class PositionSlotEmbeddable {

    private String position;
    private String proficiency;
    private int capacity;

    @ElementCollection
    private List<String> acceptedUserIds = new ArrayList<>();

    public PositionSlotEmbeddable() {}

    public PositionSlotEmbeddable(String position, String proficiency, int capacity, List<String> acceptedUserIds) {
        this.position = position;
        this.proficiency = proficiency;
        this.capacity = capacity;
        this.acceptedUserIds = acceptedUserIds != null ? acceptedUserIds : new ArrayList<>();
    }

    // getter
    public String getPosition() { return position; }
    public String getProficiency() { return proficiency; }
    public int getCapacity() { return capacity; }
    public List<String> getAcceptedUserIds() { return acceptedUserIds; }
}
