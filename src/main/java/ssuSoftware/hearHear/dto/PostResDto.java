package ssuSoftware.hearHear.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.hearHear.entity.Music;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.entity.enums.EmotionType;
import ssuSoftware.hearHear.entity.enums.GenreType;
import ssuSoftware.hearHear.entity.enums.WeatherType;
import ssuSoftware.hearHear.entity.enums.WithType;

public class PostResDto {

    private PostResDto(){}

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class PostInfo{
        private Long postId;
        private String writer;
        private String title;
        private String artist;
        private String cover;
        private GenreType genreType;
        private WithType withType;
        private Integer temp;
        private WeatherType weatherType;
        private EmotionType emotionType;
        private String content;
        private Double longitude;
        private Double latitude;
        private Integer distance;
        private Integer likeCount;
        private Boolean isLiked;

        public static PostInfo fromEntity(Post post, Integer distance, Integer heartCount, Boolean isHearted){
            Music music = post.getMusic();
            User writer = post.getUser();
            return new PostInfo(post.getId(), writer.getNickname(), music.getTitle(), music.getArtist(), music.getCover()
                    , post.getGenreType(), post.getWithType(), post.getTemp(), post.getWeatherType()
                    , post.getEmotionType(), post.getContent(), post.getLongitude(), post.getLatitude()
                    , distance, heartCount, isHearted);
        }
    }

}
