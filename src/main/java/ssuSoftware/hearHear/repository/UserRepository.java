package ssuSoftware.hearHear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssuSoftware.hearHear.entity.User;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    boolean existsByProviderId(Long id);

    Optional<User> findByProviderId(Long providerId);


}

