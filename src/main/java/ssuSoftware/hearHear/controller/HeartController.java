package ssuSoftware.hearHear.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.HeartRepository;
import ssuSoftware.hearHear.repository.PostRepository;
import ssuSoftware.hearHear.repository.UserRepository;
import ssuSoftware.hearHear.util.SecurityUtil;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class HeartController {


    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;


    @GetMapping("/post/{postId}/heart")
    public void saveHeart(@PathVariable Long postId){

        User user = securityUtil.getUser();
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("Post not found"));

        Heart heart = new Heart(user, post);
        heartRepository.save(heart);

    }

    @DeleteMapping("/post/{postId}/heart")
    public void deleteHeart(@PathVariable Long postId){

        User user = securityUtil.getUser();
        Long userId = user.getId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("Post not found"));
        Heart heart = heartRepository.findByPostAndUser(post, user);
        heartRepository.delete(heart);
    }


    @GetMapping("/post/heart/list")
    public ResponseEntity<List<Post>> getHeartList(){

        User user = securityUtil.getUser();
        List<Post> postList = heartRepository.findAllByUser(user);

        return ResponseEntity.ok().body(postList);
    }
}
