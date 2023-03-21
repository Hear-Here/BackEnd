package ssuSoftware.hearHear.entity;

import play.go.entity.enumed.Authority;
import play.go.entity.enumed.OauthProvider;

import javax.persistence.*;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;
    private String nickname;
    private String email;





    private String profilePhotoLink;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;


}
