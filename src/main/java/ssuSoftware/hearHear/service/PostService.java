package ssuSoftware.hearHear.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssuSoftware.hearHear.dto.PostReqDto;
import ssuSoftware.hearHear.entity.Music;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.MusicRepository;
import ssuSoftware.hearHear.repository.PostRepository;

import static ssuSoftware.hearHear.dto.PostReqDto.*;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MusicRepository musicRepository;

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
}
