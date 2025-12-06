package goonerd.devhub.common.vo;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApiResponseVo <T> {

    private boolean success;
    private String code;
    private String message;
    private Object param;
    private T data;

    public ApiResponseVo(boolean success, String code, String message, Object param, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.param = param;
        this.data = data;
    }

    public static <T> ApiResponseVo<T> success(SuccessCodeEnum codeEnum, Object param, T data) {
        return new ApiResponseVo<>(true, codeEnum.getCode(), codeEnum.getMessage(), param, data);
    }

    public static <T> ApiResponseVo<T> fail(ErrorCodeEnum codeEnum, Object param, T data) {
        return new ApiResponseVo<>(false, codeEnum.getCode(), codeEnum.getMessage(), param, data);
    }
}