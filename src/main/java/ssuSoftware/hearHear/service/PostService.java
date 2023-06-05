package ssuSoftware.hearHear.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssuSoftware.hearHear.dto.PostReqDto;
import ssuSoftware.hearHear.dto.PostResDto;
import ssuSoftware.hearHear.entity.Heart;
import ssuSoftware.hearHear.entity.Music;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.HeartRepository;
import ssuSoftware.hearHear.repository.MusicRepository;
import ssuSoftware.hearHear.repository.PostRepository;
import ssuSoftware.hearHear.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;

import static ssuSoftware.hearHear.dto.PostReqDto.*;
import static ssuSoftware.hearHear.dto.PostResDto.*;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MusicRepository musicRepository;
    private final HeartRepository heartRepository;
    private final SecurityUtil securityUtil;


    private static final int EARTH_RADIUS = 6371; //km

    public Long uploadPost(UploadPost uploadPost, User user) {
        //Music 테이블에 없으면 추가
        if(!musicRepository.existsByTitleAndArtist(uploadPost.getTitle(), uploadPost.getArtist())){
            Music music  = Music.builder().title(uploadPost.getTitle()).artist(uploadPost.getArtist())
                            .cover(uploadPost.getCover()).songId(uploadPost.getSongId()).build();
            musicRepository.save(music);
        }
        Music music = musicRepository.findByTitleAndArtist(uploadPost.getTitle(), uploadPost.getArtist())
                .orElseThrow(()-> new IllegalArgumentException("not found music"));

        Post post = Post.builder().user(user).content(uploadPost.getContent()).emotionType(uploadPost.getEmotionType())
                .genreType(uploadPost.getGenreType()).withType(uploadPost.getWithType())
                .weatherType(uploadPost.getWeatherType()).latitude(uploadPost.getLatitude())
                .longitude(uploadPost.getLongitude()).music(music).temp(uploadPost.getTemp())
                .build();
        return postRepository.save(post).getId();
    }


    public PostInfo findPostById(User user, Long postId, Double latitude,Double longitude) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalStateException("Could not find post"));
        int distance = calculateDistance(latitude, longitude, post.getLatitude(), post.getLongitude());
        int heartCount = post.getHeartList().size();
        boolean isHearted = heartRepository.existsByPostAndUser(post, user);
        return PostInfo.fromEntity(post, distance, heartCount, isHearted);
    }

    protected int calculateDistance(double userLat, double userLon, double postLat, double postLon) {
        double dLat = Math.toRadians(postLat - userLat);
        double dLon = Math.toRadians(postLon - userLon);

        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(userLat))* Math.cos(Math.toRadians(postLat))* Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (int)Math.round(EARTH_RADIUS* c * 1000); // Distance in m
    }


    public List<PostInfo> findAllPost(User user, Double latitude, Double longitude) {
        List<Post> postList = postRepository.findAll();
        List<PostInfo> postInfoList = new ArrayList<>();
        for (Post  post : postList) {
            int distance = calculateDistance(latitude, longitude, post.getLatitude(), post.getLongitude());
            int heartCount = post.getHeartList().size();
            boolean isHearted = heartRepository.existsByPostAndUser(post, user);
            PostInfo postInfo = PostInfo.fromEntity(post, distance, heartCount, isHearted);
            postInfoList.add(postInfo);
        }
        return postInfoList;
    }

    public Long deletePost(User user, Long postId) throws IllegalAccessException{
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if(post.getUser()==user){
            postRepository.delete(post);
            return postId;
        }else{
            throw new IllegalAccessException("not post owner");
        }
    }

    public void updatePostContent(User user, Long postId, String updateContent) throws IllegalAccessException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if(post.getUser()==user){
            post.updateContent(updateContent);
            postRepository.save(post);
        }else{
            throw new IllegalAccessException("not post owner");
        }
    }


}
