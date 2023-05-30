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
    private Long musicId;
    private String content;
    private Double latitude;
    private Double longitude;

    public PostResponseDTO(Post post){
        this.postId = post.getId();
        this.userEmail = post.getUser().getEmail();
        this.musicId = post.getMusic().getId();
        this.content = post.getContent();
        this.latitude = post.getLatitude();
        this.longitude = post.getLongitude();
    }
}
