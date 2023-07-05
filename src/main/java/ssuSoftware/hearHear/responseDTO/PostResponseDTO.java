package ssuSoftware.hearHear.responseDTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.hearHear.entity.Music;
import ssuSoftware.hearHear.entity.Post;
import ssuSoftware.hearHear.entity.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDTO {
    private Long postId;
    private String userEmail;
    private String musicTittle;
    private String musicArtist;
    private String musicCover;
    private Double latitude;
    private Double longitude;

    public PostResponseDTO(Post post){
        this.postId = post.getId();
        this.userEmail = post.getUser().getEmail();
        this.musicTittle = post.getMusic().getTitle();
        this.musicArtist = post.getMusic().getArtist();
        this.musicCover = post.getMusic().getCover();
        this.latitude = post.getLatitude();
        this.longitude = post.getLongitude();
    }
}
