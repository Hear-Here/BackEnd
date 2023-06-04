package ssuSoftware.hearHear.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.HeartRepository;
import ssuSoftware.hearHear.repository.PostRepository;
import ssuSoftware.hearHear.repository.UserRepository;
import ssuSoftware.hearHear.util.SecurityUtil;


@Service
@RequiredArgsConstructor
public class HeartService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final SecurityUtil securityUtil;

    public Heart saveHeart(Long postId) {
        User user = securityUtil.getUser();
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("Post not found"));
      if ( heartRepository.findByPostAndUser(post, user)==null){
          Heart heart = new Heart(user, post);
          heartRepository.save(heart);
            return heart;
      }
      else{
          Heart heart = heartRepository.findByPostAndUser(post, user);
          heartRepository.save(heart);
          return heart;
      }


    }

}
