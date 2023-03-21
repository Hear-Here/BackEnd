package play.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import play.go.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByemail(String email);

    boolean existUserByUserIdOrEmail(String providerId, String email);
}

