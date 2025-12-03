package goonerd.devhub.common.exception;

import goonerd.devhub.common.devhubenum.ErrorCodeEnum;

public class DomainException extends Throwable{

    ErrorCodeEnum errorCodeEnum;
    public String message;

    public DomainException(ErrorCodeEnum errorCodeEnum, String message) {
        this.errorCodeEnum = errorCodeEnum;
        this.message = message;
    }
}