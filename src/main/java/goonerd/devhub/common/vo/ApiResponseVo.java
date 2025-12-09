package goonerd.devhub.common.vo;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseVo <T> {

    private boolean success;
    private String code;
    private Object param;
    private T data;
    private ErrorResponseVo error;
    
    public static <T> ApiResponseVo<T> successWithParamAndData(SuccessCodeEnum codeEnum, Object param, T data) {
        return ApiResponseVo.<T>builder()
                .success(true)
                .code(codeEnum.getCode())
                .param(param)
                .data(data)
                .build();
    }
    
    public static <T> ApiResponseVo<T> successWithParam(SuccessCodeEnum codeEnum, Object param) {
        return ApiResponseVo.<T>builder()
                .success(true)
                .code(codeEnum.getCode())
                .param(param)
                .build();
    }

    public static <T> ApiResponseVo<T> successWithData(SuccessCodeEnum codeEnum, T data) {
        return ApiResponseVo.<T>builder()
                .success(true)
                .code(codeEnum.getCode())
                .data(data)
                .build();
    }

    public static ApiResponseVo<Void> successWithoutParamAndData(SuccessCodeEnum codeEnum) {
        return ApiResponseVo.<Void>builder()
                .success(true)
                .code(codeEnum.getCode())
                .build();
    }
    
    public static <T> ApiResponseVo<T> failureWithParam(ErrorCodeEnum errorCode, Object param) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .param(param)
                .error(ErrorResponseVo.of(errorCode))
                .build();
    }

    public static <T> ApiResponseVo<T> failureWithData(ErrorCodeEnum errorCode, T data) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .data(data)
                .error(ErrorResponseVo.of(errorCode))
                .build();
    }
    
    public static <T> ApiResponseVo<T> failureWithoutParam(ErrorCodeEnum errorCode) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .error(ErrorResponseVo.of(errorCode))
                .build();
    }

    public static <T> ApiResponseVo<T> failureFromThrowable(Throwable throwable) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(ErrorCodeEnum.UNKNOWN_FAIL.getCode())
                .error(ErrorResponseVo.of(throwable))
                .build();
    }

    public static <T> ApiResponseVo<T> failureFromFilter(Throwable throwable) {
        Map<String, Object> param = Map.of("exception", throwable.getClass().getSimpleName());

        return ApiResponseVo.<T>builder()
                .success(false)
                .code(ErrorCodeEnum.UNKNOWN_FAIL.getCode())
                .param(param)
                .error(ErrorResponseVo.of(throwable))
                .build();
    }
}