package goonerd.devhub.common.utils;

import goonerd.devhub.common.devhubenum.ErrorCodeEnum;
import goonerd.devhub.common.devhubenum.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseBuilder {

    public <T> ResponseEntity<ApiResponseVo<?>> success(SuccessCodeEnum codeEnum, T param, T data) {
        ApiResponseVo<T> body = new ApiResponseVo<>(true, codeEnum.getCode(), codeEnum.getMessage(), param, data);
        return ResponseEntity.ok(body);
    }

    public <T> ResponseEntity<ApiResponseVo<?>> fail(ErrorCodeEnum codeEnum, T param, T data) {
        ApiResponseVo<T> body = new ApiResponseVo<>(false, codeEnum.getCode(), codeEnum.getMessage(), param, data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}