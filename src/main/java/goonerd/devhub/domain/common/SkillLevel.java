package goonerd.devhub.domain.common;

public enum SkillLevel {
    JUNIOR(1),
    MID(2),
    SENIOR(3);

    private final int score;

    SkillLevel(int score) {
        this.score = score;
    }

    public boolean isAtLeast(SkillLevel other) {
        return this.score >= other.score;
    }
}
