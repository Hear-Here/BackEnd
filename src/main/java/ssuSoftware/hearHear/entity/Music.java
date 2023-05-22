package ssuSoftware.hearHear.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;

    private String title;

    private String artist;

    private String corver;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder
    public Music(String title, String artist, String corver) {
        this.title = title;
        this.artist = artist;
        this.corver = corver;
    }
}
