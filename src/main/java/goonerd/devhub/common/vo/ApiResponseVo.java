package goonerd.devhub.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApiResponseVo <T> {

    private boolean success;
    private String code;
    private String message;
    private T param;
    private T data;

    public ApiResponseVo(boolean success, String code, String message, T param, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.param = param;
        this.data = data;
    }
}