package goonerd.devhub.domain.project;

public enum RecruitmentType {
    NORMAL,
    EXTRA;

    public static RecruitmentType from(String value) {
        try {
            return RecruitmentType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 모집 유형입니다.");
        }
    }
}