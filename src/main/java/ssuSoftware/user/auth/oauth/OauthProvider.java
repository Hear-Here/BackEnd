package ssuSoftware.user.auth.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public enum OauthProvider {
    kakao("kakao"),
    google("google");

    private String value;

    public boolean isEqual(String provider){
        return this.value.equalsIgnoreCase(provider);
    }
    }
