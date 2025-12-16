package goonerd.devhub.domain.project;

import java.util.Arrays;

public enum ProjectProgressType {
    ONLINE("online"),
    OFFLINE("offline"),
    HYBRID("hybrid");

    private final String value;

    ProjectProgressType(String value) {
        this.value = value;
    }

    public static ProjectProgressType from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("프로젝트 진행 방식은 비어 있을 수 없습니다.");
        }

        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("잘못된 프로젝트 진행 방식입니다.")
                );
    }
}