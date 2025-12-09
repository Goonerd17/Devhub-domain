package goonerd.devhub.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCodeEnum {

    VALIDATION_FAIL("ERR.DVH.000", "검증 오류", BAD_REQUEST),

    READ_FAIL("ERR.DVH.001", "조회 오류", INTERNAL_SERVER_ERROR),
    CREATE_FAIL("ERR.DVH.002", "작성 오류",INTERNAL_SERVER_ERROR),
    UPDATE_FAIL("ERR.DVH.003", "수정 오류",INTERNAL_SERVER_ERROR),
    DELETE_FAIL("ERR.DVH.004", "삭제 오류",INTERNAL_SERVER_ERROR),

    TOKEN_EXPIRED("ERR.DVH.010", "토큰이 만료되었습니다.", BAD_REQUEST),
    TOKEN_INVALID("ERR.DVH.011", "유효하지 않은 토큰입니다.", BAD_REQUEST),
    MISSING_AUTH_HEADER("ERR.DVH.012", "Authorization Header가 없습니다.", BAD_REQUEST),
    UNSUPPORTED_TOKEN("ERR.DVH.013", "지원되지 않는 토큰 형식입니다.", BAD_REQUEST),
    AUTH_INVALID("ERR.DVH.014", "유효하지 않은 인증 정보입니다.", UNAUTHORIZED),
    AUTH_FAIL("ERR.DVH.015", "인증에 실패했습니다.", UNAUTHORIZED),
    SIGNUP_FAIL("ERR.DVH.016", "회원가입에 실패했습니다.", BAD_REQUEST),
    LOGIN_FAIL("ERR.DVH.017", "로그인에 실패했습니다.", BAD_REQUEST),
    DUPLICATE_USERNAME("ERR.DVH.018", "중복된 유저명을 사용할 수는 없습니다.", BAD_REQUEST),

    PROJECT_RECRUITCOUNT_FAIL("ERR.DVH.051", "모집 인원은 최소 1명 이상이어야 합니다.", BAD_REQUEST),
    PROJECT_PERIOD_FAIL("ERR.DVH.052", "프로젝트 종료일은 시작일 이후여야 합니다.",BAD_REQUEST),
    PROJECT_DESCRIPTION_FAIL("ERR.DVH.052", "프로젝트 설명은 100자를 넘길 수 없습니다.",BAD_REQUEST),

    UNKNOWN_FAIL("ERR.DVH.999", "원인 미상의 에러가 발생했습니다.",BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCodeEnum(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}