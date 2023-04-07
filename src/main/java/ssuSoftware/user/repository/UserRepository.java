package ssuSoftware.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssuSoftware.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existUserByUserIdAndEmail(String providerId, String email);

    Optional<User> findByUserId(String providerId);

    boolean existUserByUserIdOrEmail(String providerId, String email);

    boolean existUserByNickname(String nickname);

    Optional<User> findByUSerIdAndEmail(String providerId, String email);
}

