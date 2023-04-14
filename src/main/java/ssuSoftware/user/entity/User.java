package ssuSoftware.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ssuSoftware.hearHear.core.base.Default;
import ssuSoftware.user.entity.kind.Authority;
import ssuSoftware.user.entity.kind.Gender;


import javax.persistence.*;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

@Getter
@Entity
@ToString
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
    private String provider;

    @Builder
    public User(String name, String email, String profilePhotoLink, Gender gender, Authority authority, String provider) {
        this.name = name;
        this.email = email;
        this.profilePhotoLink = profilePhotoLink;
        this.gender = gender;
        this.authority = authority;
        this.provider = provider;
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
    public User updateProfileImage(String profilePhotoLink) {
        this.profilePhotoLink = profilePhotoLink;
        return this;
    }
    public User updateName(String name) {
        this.name = name;
        return this;
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
