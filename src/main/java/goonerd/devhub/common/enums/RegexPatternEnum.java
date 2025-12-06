package goonerd.devhub.common.enums;

public enum RegexPatternEnum {

    USERNAME("^[a-zA-Z0-9가-힣_]{1,15}$", "사용자명은 한글,영문,숫자,_만 사용 가능하며 최대 15자까지 가능합니다.");

    private final String regexp;
    private final String message;

    RegexPatternEnum(String regexp, String message) {
        this.regexp = regexp;
        this.message = message;
    }

    public String regexp() {
        return regexp;
    }

    public String message() {
        return message;
    }
}
