package goonerd.devhub.common.auth.repository;

import goonerd.devhub.common.auth.vo.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {}