package goonerd.devhub.domain.project;

import java.util.Arrays;

public enum ProjectStatus {

    RECRUITING("recruiting"),
    COMPLETED("completed"),
    CLOSED("closed"),
    CANCELLED("cancelled");

    private final String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    public boolean isRecruitable() {
        return this == RECRUITING;
    }

    public boolean isFinished() {
        return this != RECRUITING;
    }

    public static ProjectStatus from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("프로젝트 상태는 비어 있을 수 없습니다.");
        }

        return Arrays.stream(values())
                .filter(status -> status.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("잘못된 프로젝트 상태입니다.")
                );
    }
}