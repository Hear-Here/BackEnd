package ssuSoftware.hearHear.entity;

import lombok.Getter;
import ssuSoftware.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
