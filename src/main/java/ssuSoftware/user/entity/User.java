package ssuSoftware.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.user.auth.oauth.OauthProvider;
import ssuSoftware.hearHear.core.base.Default;
import ssuSoftware.user.entity.kind.Authority;
import ssuSoftware.user.entity.kind.Gender;


import javax.persistence.*;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

@Getter
@Entity
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String name;
    private String nickname;
    private String email;



    private String profilePhotoLink;

    @Column(columnDefinition = "LONGTEXT")
    private String userLink;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    @Builder
    public User( String email, OauthProvider provider, String nickname {
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
        setDefaultValues();
    }
    private void setDefaultValues() {
        this.authority = Authority.USER;
        this.gender = Gender.MALE;
        this.profilePhotoLink = Default.PHOTO_LINK;
    }

    public boolean isAnonymous(){ return false;}

    public void makeAnonymous(){this.authority = Authority.ANOYMOUS}

    public void modify(String name, String nickname, String profilePhoto, String userLink){
        if(hasText(name)){
            this.name = name;
        }
        if(hasText(nickname)){
            this.nickname = nickname;
        }
        if(hasText(profilePhotoLink)){
            this.profilePhotoLink = profilePhotoLink;
        }
        if(hasText(userLink)){
            this.userLink = userLink;
        }
    }
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
    public void setProfilePhoto(String profilePhotoLink) {
        this.profilePhotoLink = profilePhotoLink;
    }
    public boolean hasNullOfMandatoryFields() {
        return isNull(this.email) ||  isNull(authority)
                || isNull(gender) || isNull(profilePhotoLink) || isNull(provider);
    }

    public static class NullUser extends User{

        public NullUser(){
            super();
            super.makeAnonymous();
        }
        @Override
        public boolean isAnonymous(){return true;}
    }
}
