package goonerd.devhub.common.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class DomainException extends RuntimeException{

    private final String errorCode;
    private String message;

    public DomainException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}