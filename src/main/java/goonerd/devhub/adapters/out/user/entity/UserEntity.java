package goonerd.devhub.adapters.out.user.entity;

import goonerd.devhub.adapters.out.common.BaseEntity;
import goonerd.devhub.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_users_email",
                        columnNames = "email"
                )
        }
)
public class UserEntity extends BaseEntity {

    @Id @Column(length = 32, nullable = false, unique = true)
    private String userGuid;

    @Column(nullable = false)
    private String email;

    @Column
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}