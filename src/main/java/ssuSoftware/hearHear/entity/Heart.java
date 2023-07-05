package ssuSoftware.hearHear.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Heart(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Heart of(User user, Post post){
        this.user =user;
        this.post = post;
        return new Heart;
    }

}
