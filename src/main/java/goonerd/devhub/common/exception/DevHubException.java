package goonerd.devhub.common.exception;

import goonerd.devhub.common.devhubenum.ErrorCodeEnum;

public class DevHubException extends Throwable{

    ErrorCodeEnum errorCodeEnum;
    public String message;

    public DevHubException(ErrorCodeEnum errorCodeEnum, String message) {
        this.errorCodeEnum = errorCodeEnum;
        this.message = message;
    }
}