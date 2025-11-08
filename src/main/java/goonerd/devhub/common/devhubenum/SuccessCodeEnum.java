package goonerd.devhub.common.devhubenum;

import lombok.Getter;

@Getter
public enum SuccessCodeEnum {

    GUESTBOOK_LIST_SUCCESS("S001", "방명록 목록 조회 성공"),
    GUESTBOOK_CREATE_SUCCESS("S002", "방명록 작성 성공");

    private final String code;
    private final String message;

    SuccessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}