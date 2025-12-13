package goonerd.devhub.domain.application;

import goonerd.devhub.domain.common.SkillLevel;

import java.util.Objects;

public record PositionRequirement(
        String position,
        SkillLevel skillLevel
) {
    public PositionRequirement {
        Objects.requireNonNull(position, "position은 필수입니다");
        Objects.requireNonNull(skillLevel, "skillLevel은 필수입니다");
    }
}
