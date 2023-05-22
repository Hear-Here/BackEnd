package ssuSoftware.hearHear.entity;

import lombok.*;
import ssuSoftware.hearHear.entity.kind.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;
    private Long providerId;
    private String name;
    private String nickName;
    private String kakaoAccessToken;
    private String kakaoRefreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "kakao_update")
    private LocalDateTime kakaoUpdate;

    public void setKakaoUpdate(){
        this.kakaoUpdate = LocalDateTime.now();
    }
    public void updateNickName(String nickName){
        this.nickName = nickName;
    }
    public void setKakaoToken(String kakaoAccessToken, String kakaoRefreshToken){
        this.kakaoAccessToken = kakaoAccessToken;
        this.kakaoRefreshToken = kakaoRefreshToken;
    }
}