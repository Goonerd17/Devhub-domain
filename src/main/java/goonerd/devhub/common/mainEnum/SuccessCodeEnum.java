package goonerd.devhub.common.mainEnum;

import lombok.Getter;

@Getter
public enum SuccessCodeEnum {

    PROJECT_READ_SUCCESS("SUC.DVH.001", "조회 성공"),
    PROJECT_CREATE_SUCCESS("SUC.DVH.002", "생성 성공"),
    PROJECT_UPDATE_SUCCESS("SUC.DVH.003", "수정 성공"),
    PROJECT_DELETE_SUCCESS("SUC.DVH.004", "삭제 성공");

    private final String code;
    private final String message;

    SuccessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}