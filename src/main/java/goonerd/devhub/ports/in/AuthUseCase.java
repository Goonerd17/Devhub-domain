package goonerd.devhub.ports.in;

public interface AuthUseCase {
    String refreshAccessToken(String refreshToken);
}
