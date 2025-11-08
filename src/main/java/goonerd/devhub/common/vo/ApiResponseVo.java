package goonerd.devhub.common.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponseVo <T> {

    private boolean isSuccess;
    private String code;
    private String message;
    private T param;
    private T data;

    public ApiResponseVo(boolean isSuccess, String code, String message, T param, T data) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.param = param;
        this.data = data;
    }
}