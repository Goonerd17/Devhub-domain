package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.auth.dto.TokenResponseDto;

public interface AuthUseCase {
    TokenResponseDto refreshAccessToken(String refreshToken);
}
