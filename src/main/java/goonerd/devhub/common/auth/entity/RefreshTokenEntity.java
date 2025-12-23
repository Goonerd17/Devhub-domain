package goonerd.devhub.common.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 500)
    private String refreshToken;

    protected RefreshTokenEntity(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public static RefreshTokenEntity of(String userId, String refreshToken) {
        return new RefreshTokenEntity(userId, refreshToken);
    }

    public void rotate(String newToken) {
        this.refreshToken = newToken;
    }
}
