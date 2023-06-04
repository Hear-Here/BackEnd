package ssuSoftware.hearHear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {

}
