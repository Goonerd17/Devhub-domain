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
    private String message;
    private Object param;
    private T data;
    private ErrorResponseVo error;
    
    public static <T> ApiResponseVo<T> successWithParamAndData(SuccessCodeEnum codeEnum, Object param, T data) {
        return ApiResponseVo.<T>builder()
                .success(true)
                .code(codeEnum.getCode())
                .message(codeEnum.getMessage())
                .param(param)
                .data(data)
                .build();
    }
    
    public static <T> ApiResponseVo<T> successWithParam(SuccessCodeEnum codeEnum, Object param) {
        return ApiResponseVo.<T>builder()
                .success(true)
                .code(codeEnum.getCode())
                .message(codeEnum.getMessage())
                .param(param)
                .build();
    }

    public static <T> ApiResponseVo<T> successWithData(SuccessCodeEnum codeEnum, T data) {
        return ApiResponseVo.<T>builder()
                .success(true)
                .code(codeEnum.getCode())
                .message(codeEnum.getMessage())
                .data(data)
                .build();
    }

    public static ApiResponseVo<Void> successWithoutParamAndData(SuccessCodeEnum codeEnum) {
        return ApiResponseVo.<Void>builder()
                .success(true)
                .code(codeEnum.getCode())
                .message(codeEnum.getMessage())
                .build();
    }
    
    public static <T> ApiResponseVo<T> failureWithParam(ErrorCodeEnum errorCode, Object param) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .param(param)
                .error(ErrorResponseVo.of(errorCode))
                .build();
    }
    
    public static <T> ApiResponseVo<T> failureWithoutParam(ErrorCodeEnum errorCode) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .error(ErrorResponseVo.of(errorCode))
                .build();
    }

    public static <T> ApiResponseVo<T> failureFromThrowable(Throwable throwable) {
        return ApiResponseVo.<T>builder()
                .success(false)
                .code(ErrorCodeEnum.UNKNOWN_FAIL.getCode())
                .message(throwable.getMessage())
                .error(ErrorResponseVo.of(throwable))
                .build();
    }

    public static <T> ApiResponseVo<T> failureFromFilter(Throwable throwable) {
        String message = throwable.getMessage();
        if (message == null || message.isBlank()) {
            message = ErrorCodeEnum.UNKNOWN_FAIL.getMessage();
        }

        Map<String, Object> param = Map.of(
                "exception", throwable.getClass().getSimpleName()
        );

        return ApiResponseVo.<T>builder()
                .success(false)
                .code(ErrorCodeEnum.UNKNOWN_FAIL.getCode())
                .message(message)
                .param(param)
                .error(ErrorResponseVo.of(throwable))
                .build();
    }
}