package goonerd.devhub.adapters.out.user;

import goonerd.devhub.adapters.out.common.BaseEntity;
import goonerd.devhub.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id @GeneratedValue
    @UuidGenerator @Column(length = 36, nullable = false, unique = true)
    private String userGuid;

    @Column(unique = true)
    private String userId;

    @Column
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}