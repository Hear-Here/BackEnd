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
//    private Map<String, Object> attributes;
//
//    public KakaoUserInfo(Map<String, Object> attributes) {
//        this.attributes = attributes;
//    }
//
//
//    public String getEmail() {
//       return (String) getKakaoAccount().get("email");
//    }
//
//    public Long getId() {
//        return Long.parseLong(String.valueOf(attributes.get("id")));
//    }
//
//    public String getName() {
//        return (String) getKakaoAccount().get("name");
//    }
//
//
//    public String getNickName() {
//        return (String) getProfile().get("nickname");
//    }
//    // email, nickname,  profile_image는 해당 정보는 kakao_account라는 JSON객체로 주어진다.
//    public Map<String, Object> getKakaoAccount(){
//        return(Map<String, Object>) attributes.get("kakao_account");
//    }
//
//    public Map<String, Object> getProfile(){
//        return (Map<String, Object>) getKakaoAccount().get("profile");
//    }
//
//
//
//}
