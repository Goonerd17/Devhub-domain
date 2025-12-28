package goonerd.devhub.domains.project;

public enum ProjectStatus {

    RECRUITING("recruiting"),
    COMPLETED("completed"),
    CLOSED("closed"),
    CANCELLED("cancelled");

    private final String value;

    ProjectStatus(String value) {
        this.value = value;
    }
}