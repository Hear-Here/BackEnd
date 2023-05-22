package ssuSoftware.hearHear.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

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

    private String content;

    private Double latitude;

    private Double longitude;

    @Builder
    public Post(User user, Music music, String content, Double latitude, Double longitude) {
        this.user = user;
        this.music = music;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
