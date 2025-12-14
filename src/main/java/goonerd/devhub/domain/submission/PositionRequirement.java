package goonerd.devhub.domain.submission;

import goonerd.devhub.domain.common.SkillLevel;

import java.util.Objects;

public class PositionRequirement {

    private String position;
    private SkillLevel skillLevel;

    public PositionRequirement(String position, SkillLevel skillLevel) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("position은 필수입니다.");
        }
        this.position = position;
        this.skillLevel = Objects.requireNonNull(skillLevel, "skillLevel은 필수입니다.");
    }

    public static PositionRequirement of(String position, SkillLevel skillLevel) {
        return new PositionRequirement(position, skillLevel);
    }

    public static PositionRequirement fromApplyApplicationCommand(String position, SkillLevel skillLevel) {
        return new PositionRequirement(position, skillLevel);
    }

    public String getPosition() {
        return position;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }
}