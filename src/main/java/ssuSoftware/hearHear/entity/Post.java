package ssuSoftware.hearHear.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String music_name;

    private String music_artist;

    private String music_cover;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime currentDateTime = LocalDateTime.now();

    private String content;

    private String userId;

    private Double latitude;

    private Double longitude;


}
