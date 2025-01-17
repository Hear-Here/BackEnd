package ssuSoftware.hearHear.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssuSoftware.hearHear.dto.PostResDto;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.HeartRepository;
import ssuSoftware.hearHear.repository.PostRepository;
import ssuSoftware.hearHear.repository.UserRepository;
import ssuSoftware.hearHear.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;

import static ssuSoftware.hearHear.dto.PostResDto.*;


@Service
@RequiredArgsConstructor
public class HeartService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final PostService postService;
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

    public List<PostInfo> getHeatList(User user, Double latitude, Double longitude) {
        List<Heart> heartList = heartRepository.findAllByUser(user);
        List<PostInfo> postInfoList = new ArrayList<>();
        for (Heart  heart : heartList) {
            Post post = heart.getPost();
            int distance = postService.calculateDistance(latitude, longitude, post.getLatitude(), post.getLongitude());
            int heartCount = post.getHeartList().size();
            boolean isHearted = heartRepository.existsByPostAndUser(post, user);
            PostInfo postInfo = PostInfo.fromEntity(post, distance, heartCount, isHearted);
            postInfoList.add(postInfo);
        }
        return postInfoList;

    }
}
