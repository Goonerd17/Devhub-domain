package goonerd.devhub.common.enums;

import org.springframework.http.HttpStatus;

public enum JwtStatusEnum {

    VALID(HttpStatus.OK, "유효한 토큰입니다."),
    INVALID(HttpStatus.FORBIDDEN, "유효하지않은 토큰입니다."),
    EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");

    private final HttpStatus status;
    private final String message;

    JwtStatusEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() { return status; }

    public String getMessage() { return message; }
}