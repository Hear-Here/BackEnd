package ssuSoftware.hearHear.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart,Long> {




    Heart findByPostAndUser(Post post, User user);


    List<Post> findAllByUser(User user);
}
