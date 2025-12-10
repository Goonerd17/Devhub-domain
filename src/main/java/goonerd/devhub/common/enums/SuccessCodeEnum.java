package goonerd.devhub.common.enums;

import lombok.Getter;

@Getter
public enum SuccessCodeEnum {

    READ_SUCCESS("SUC.DVH.0001", "조회 성공"),
    CREATE_SUCCESS("SUC.DVH.0002", "생성 성공"),
    UPDATE_SUCCESS("SUC.DVH.0003", "수정 성공"),
    DELETE_SUCCESS("SUC.DVH.0004", "삭제 성공"),

    SIGNUP_SUCCESS("SUC.DVH.0010", "회원가입 성공"),
    LOGIN_SUCCESS("SUC.DVH.0011", "로그인 성공");

    private final String code;
    private final String message;

    SuccessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}