package ssuSoftware.user.oauth.dto;

import org.springframework.security.oauth2.core.user.OAuth2User;
import ssuSoftware.user.entity.User;
import ssuSoftware.user.entity.kind.Authority;

import java.util.Map;

public class KakaoOAuth2UserAttribute extends OAuth2UserAttribute{

    private static final String KAKAO_PROVIDER_ID = "kakao";
    private static final String KAKAO_ACCOUNT_KEY = "kakao_account";
    private static final String KAKAO_PROFILE_KEY = "profile";

    private final Map<String, Object> kakaoAccount;
    private final Map<String, Object> profile;
    public KakaoOAuth2UserAttribute(Map<String, Object> attributes) {
        super(attributes);
        this.kakaoAccount = (Map<String, Object>) attributes.get(KAKAO_ACCOUNT_KEY);
        this.profile = (Map<String, Object>) kakaoAccount.get(KAKAO_PROFILE_KEY);
    }


    @Override
    public String getEmail() {
        return kakaoAccount.get("email").toString();
    }

    @Override
    public String getNickname() {
        return profile.get("nickname").toString();
    }

    @Override
    public String getProfileImage() {
        return profile.get("profile_image_url").toString();
    }

    @Override
    public User toEntity() {
        return User.builder()
                .authority(Authority.USER)
                .provider(KAKAO_PROVIDER_ID)
                .profilePhotoLink(getProfileImage())
                .name(getNickname())
                .email(getEmail())
                .build();
    }
}
