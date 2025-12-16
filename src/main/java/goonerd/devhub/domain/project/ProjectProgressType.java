package goonerd.devhub.domain.project;

public enum ProjectProgressType {
    ONLINE,
    OFFLINE,
    HYBRID;

    public static ProjectProgressType from(String value) {
        try {
            return ProjectProgressType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 프로젝트 진행 방식입니다.");
        }
    }

    public boolean isOnline() {
        return this == ONLINE || this == HYBRID;
    }
}