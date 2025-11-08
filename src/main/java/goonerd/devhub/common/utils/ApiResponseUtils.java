package goonerd.devhub.common.utils;

import goonerd.devhub.common.vo.ApiResponseVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponseUtils {

    public static <T> ApiResponseVo<T> success(String code, String message, T param, T response) {
        return new ApiResponseVo<>(true, code, message, param, response);
    }

    public static <T> ApiResponseVo<T> fail(String code, String message, T param, T response) {
        return new ApiResponseVo<>(false, code, message, param, response);
    }
}