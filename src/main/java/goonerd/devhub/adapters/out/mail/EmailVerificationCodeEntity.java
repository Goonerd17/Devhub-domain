package goonerd.devhub.adapters.out.mail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailVerificationCodeEntity {

    @Id @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 6)
    private String code;

    private LocalDateTime expiredAt;

    private LocalDateTime verifiedAt;

    public boolean isExpired(LocalDateTime now) {
        return expiredAt.isBefore(now);
    }

    public boolean isVerified() {
        return verifiedAt != null;
    }

    public void verify(LocalDateTime now) {
        this.code = null;       // 코드 무효화
        this.verifiedAt = now;  // 인증 완료
    }
}
