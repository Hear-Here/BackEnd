package ssuSoftware.hearHear.controller;

import lombok.RequiredArgsConstructor;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.HeartRepository;
import ssuSoftware.hearHear.repository.PostRepository;
import ssuSoftware.hearHear.repository.UserRepository;
import ssuSoftware.hearHear.responseDTO.PostResponseDTO;
import ssuSoftware.hearHear.service.HeartService;
import ssuSoftware.hearHear.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class HeartController {


    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;
    private final HeartService heartService;


    @GetMapping("/post/{postId}/heart")
    public void saveHeart(@PathVariable Long postId){
        heartService.saveHeart(postId);
    }

    @DeleteMapping("/post/{postId}/heart")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHeart(@PathVariable Long postId){

        User user = securityUtil.getUser();
        Long userId = user.getId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("Post not found"));
        Heart heart = heartRepository.findByPostAndUser(post, user);
        heartRepository.delete(heart);
    }


    @GetMapping("/post/heart/list")
    public ResponseEntity<List<PostResponseDTO>> getHeartList(){

        User user = securityUtil.getUser();
        List<Heart> heartList = heartRepository.findAllByUser(user);

        List<PostResponseDTO> postList =   new ArrayList<>();
        for(Heart heart:heartList){
            PostResponseDTO postResponseDTO = new PostResponseDTO(heart.getPost());
        postList.add(postResponseDTO);
        }
        return ResponseEntity.ok().body(postList);
    }
}
