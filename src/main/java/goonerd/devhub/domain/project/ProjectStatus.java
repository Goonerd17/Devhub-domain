package goonerd.devhub.domain.project;

public enum ProjectStatus {
    RECRUITING,
    COMPLETED,
    CLOSED,
    CANCELLED;

    public boolean isRecruitable() {
        return this == RECRUITING;
    }

    public boolean isFinished() {
        return this == COMPLETED || this == CLOSED || this == CANCELLED;
    }
}