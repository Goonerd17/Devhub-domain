package goonerd.devhub.domain.project;

import java.util.Arrays;

public enum RecruitmentType {
    NORMAL("normal"),
    EXTRA("extra");

    private final String value;

    RecruitmentType(String value) {
        this.value = value;
    }

    public static RecruitmentType from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("모집 유형은 비어 있을 수 없습니다.");
        }

        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("잘못된 모집 유형입니다.")
                );
    }
}