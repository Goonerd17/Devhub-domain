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

    public static SkillLevel fromCommand(String value) {
        try {
            return SkillLevel.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 SkillLevel입니다: " + value);
        }
    }
}
