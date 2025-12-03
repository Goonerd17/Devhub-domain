package goonerd.devhub.common.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class DomainException extends RuntimeException{

    private final String errorCode;
    private final Map<String, Object> context;
    private String message;

    public DomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.context = new HashMap<>();
    }

    public DomainException(String errorCode, String message, Map<String, Object> context) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.context = Optional.ofNullable(context).orElse(new HashMap<>());
    }

    public DomainException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
        this.context = new HashMap<>();
    }
}