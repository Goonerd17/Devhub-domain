package goonerd.devhub.common.devhubenum;

import lombok.Getter;

@Getter
public enum SuccessCodeEnum {

    POST_CREATE_SUCCESS("S001", "게시글 작성 성공");

    private final String code;
    private final String message;

    SuccessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}