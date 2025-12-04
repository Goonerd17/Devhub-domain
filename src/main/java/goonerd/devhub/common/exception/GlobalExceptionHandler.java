package goonerd.devhub.common.exception;

import goonerd.devhub.common.vo.ApiResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseVo<?>> handleDomainException(DomainException e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponseVo<>(false, "500", e.getMessage(), Collections.emptyMap(), Collections.emptyMap()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseVo<?>> handleIllegalArgument(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponseVo<>(false, "500", e.getMessage(), Collections.emptyMap(), Collections.emptyMap()));
    }
}
