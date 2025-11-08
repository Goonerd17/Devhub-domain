package goonerd.devhub.common.devhubenum;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    POST_CREATE_FAIL("F001", "게시글 작성 실패");

    private final String code;
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
