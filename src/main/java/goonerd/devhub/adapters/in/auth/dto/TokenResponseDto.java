package goonerd.devhub.adapters.in.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;

    public static TokenResponseDto of(String accessToken, String refreshToken) {
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public static TokenResponseDto reissue(String newAccessToken, String refreshToken) {
        return new TokenResponseDto(newAccessToken, refreshToken);
    }
}