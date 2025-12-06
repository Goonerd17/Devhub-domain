package goonerd.devhub.common.exception;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final ErrorCodeEnum errorCodeEnum;

    private DomainException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public static DomainException of(ErrorCodeEnum errorCode) {
        return new DomainException(errorCode);
    }
}