package ssuSoftware.hearHear.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.hearHear.entity.enums.GenreType;
import ssuSoftware.hearHear.entity.enums.EmotionType;
import ssuSoftware.hearHear.entity.enums.WeatherType;
import ssuSoftware.hearHear.entity.enums.WithType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @Enumerated(EnumType.STRING)
    private GenreType genreType;

    @Enumerated(EnumType.STRING)
    private WithType withType;

    @Enumerated(EnumType.STRING)
    private WeatherType weatherType;

    @Enumerated(EnumType.STRING)
    private EmotionType emotionType;

    private Integer temp;

    private String content;

    private Double latitude;

    private Double longitude;

    @Builder
    public Post(User user, Music music, GenreType genreType, WithType withType, WeatherType weatherType,
                EmotionType emotionType, Integer temp, String content, Double latitude, Double longitude) {
        this.user = user;
        this.music = music;
        this.genreType = genreType;
        this.withType = withType;
        this.weatherType = weatherType;
        this.emotionType = emotionType;
        this.temp = temp;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
