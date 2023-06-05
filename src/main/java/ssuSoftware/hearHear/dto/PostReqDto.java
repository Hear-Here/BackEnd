package ssuSoftware.hearHear.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.hearHear.entity.enums.EmotionType;
import ssuSoftware.hearHear.entity.enums.GenreType;
import ssuSoftware.hearHear.entity.enums.WeatherType;
import ssuSoftware.hearHear.entity.enums.WithType;

public class PostReqDto {

    private PostReqDto(){}

    @NoArgsConstructor
    @Getter
    public static class UploadPost{
        private String title;
        private String artist;
        private String cover;
        private Long songId;
        private GenreType genreType;
        private WithType withType;
        private WeatherType weatherType;
        private EmotionType emotionType;
        private Integer temp;
        private String content;
        private Double longitude;
        private Double latitude;
    }
    @NoArgsConstructor
    @Getter
    public static class UploadPostContent{
        private String content;
    }

}
