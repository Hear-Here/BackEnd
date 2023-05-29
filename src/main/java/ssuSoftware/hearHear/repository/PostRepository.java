package ssuSoftware.hearHear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssuSoftware.hearHear.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
