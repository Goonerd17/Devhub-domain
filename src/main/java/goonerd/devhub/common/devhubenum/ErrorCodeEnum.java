package goonerd.devhub.common.devhubenum;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    GUESTBOOK_LIST_FAIL("F001", "방명록 목록 조회 실패"),
    GUESTBOOK_CREATE_FAIL("F002", "방명록 작성 실패");

    private final String code;
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
