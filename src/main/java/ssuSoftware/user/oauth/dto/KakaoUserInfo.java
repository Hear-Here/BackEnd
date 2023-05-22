package ssuSoftware.user.oauth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class KakaoUserInfo {

    private Long id;
    private Kakao_account kakao_account;

    public String getProvider(){return "kakao";}
    @Getter
    @Setter
    @NoArgsConstructor
    public class Kakao_account {


        private String email;

    }



}
