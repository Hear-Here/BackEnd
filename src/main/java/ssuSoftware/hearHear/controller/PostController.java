package ssuSoftware.hearHear.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.service.PostService;
import ssuSoftware.hearHear.util.SecurityUtil;

import java.util.List;

import static ssuSoftware.hearHear.dto.PostReqDto.*;
import static ssuSoftware.hearHear.dto.PostResDto.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final SecurityUtil securityUtil;

    @PostMapping("")
    public ResponseEntity<String> uploadPost(@RequestBody UploadPost uploadPost){
        User user = securityUtil.getUser();
        postService.uploadPost(uploadPost, user);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostInfo> findPost(@PathVariable Long postId, @RequestParam Double latitude,
                                             @RequestParam Double longitude){
        User user = securityUtil.getUser();
        PostInfo postInfo = postService.findPostById(user, postId, latitude, longitude);
        return ResponseEntity.ok().body(postInfo);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostInfo>> findPost(@RequestParam Double latitude,
                                                  @RequestParam Double longitude){
        User user = securityUtil.getUser();
        List<PostInfo> postList = postService.findAllPost(user, latitude, longitude);
        return ResponseEntity.ok().body(postList);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deletePost(@PathVariable Long postId) throws IllegalAccessException {
        User user = securityUtil.getUser();
        Long deletedPostId = postService.deletePost(user, postId);
        return ResponseEntity.ok().body(deletedPostId);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePostContent(@RequestBody UploadPostContent updateContent ,
                                                    @PathVariable Long postId) throws IllegalAccessException {
        User user = securityUtil.getUser();
        postService.updatePostContent(user, postId, updateContent.getContent());
        return ResponseEntity.ok().body("Success");
    }
}
