package goonerd.devhub.common.exception;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiResponseVo<?>> handleDomainException(DomainException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponseVo.fail(e.getErrorCodeEnum(), Collections.emptyMap(), Collections.emptyMap()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseVo<?>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().
                forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                ApiResponseVo.fail(ErrorCodeEnum.VALIDATION_FAIL, Collections.emptyMap(), errors)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseVo<?>> handleException(Exception e) {
        return ResponseEntity.badRequest()
                .body(new ApiResponseVo<>(false, "500", e.getMessage(), Collections.emptyMap(), Collections.emptyMap()));
    }
}
