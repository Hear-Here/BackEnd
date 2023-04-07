package ssuSoftware.hearHear.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import ssuSoftware.user.auth.oauth.OauthProvider;
import ssuSoftware.user.entity.User;
import ssuSoftware.user.entity.kind.Authority;
import ssuSoftware.user.entity.kind.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String password;
    private String nickname;
    private String email;



    private String profilePhotoLink;


    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;



    @Builder
    public User toEntity(){
        return User.builder()
                .password(password)
                .email(email)
                .nickname(nickname)
                .authority(Authority.USER)
                .build();

    }
}
