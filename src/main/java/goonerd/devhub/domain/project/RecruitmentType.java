package goonerd.devhub.domain.project;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;

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
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_RECRUITMENT_FAIL);
        }

        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> DomainRuleException.of(ErrorCodeEnum.PROJECT_WRONG_RECRUITMENT_FAIL));
    }
}