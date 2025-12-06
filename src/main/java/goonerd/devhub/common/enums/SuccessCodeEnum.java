package goonerd.devhub.common.enums;

import lombok.Getter;

@Getter
public enum SuccessCodeEnum {

    READ_SUCCESS("SUC.DVH.001", "조회 성공"),
    CREATE_SUCCESS("SUC.DVH.002", "생성 성공"),
    UPDATE_SUCCESS("SUC.DVH.003", "수정 성공"),
    DELETE_SUCCESS("SUC.DVH.004", "삭제 성공"),

    LOGIN_SUCCESS("SUC.DVH.013", "로그인성공");

    private final String code;
    private final String message;

    SuccessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}