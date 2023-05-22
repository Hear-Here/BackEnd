package ssuSoftware.user.oauth.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class KakaoUserInfo {

    private Long id;
    private Kakao_account kakao_account;

    public String getProvider(){return "kakao";}
    @Getter
    public class Kakao_account {

        private String name;
        private String email;

        private Profile profile;

        @Getter
        public class Profile {
            private String nickname;
        }
    }

}
