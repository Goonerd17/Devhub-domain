package goonerd.devhub.domains.project;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;

import java.util.Arrays;

public enum ProgressType {
    ONLINE("online"),
    OFFLINE("offline"),
    HYBRID("hybrid");

    private final String value;

    ProgressType(String value) {
        this.value = value;
    }

    public static ProgressType from(String value) {
        if (value == null || value.isBlank()) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_PROGRESS_FAIL);
        }

        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> DomainRuleException.of(ErrorCodeEnum.PROJECT_WRONG_PROGRESS_FAIL));
    }
}