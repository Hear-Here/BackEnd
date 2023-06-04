package ssuSoftware.hearHear.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssuSoftware.hearHear.dto.PostReqDto;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.service.PostService;
import ssuSoftware.hearHear.util.SecurityUtil;

import static ssuSoftware.hearHear.dto.PostReqDto.*;

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
}
