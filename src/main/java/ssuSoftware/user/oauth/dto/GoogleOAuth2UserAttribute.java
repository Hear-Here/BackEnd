package ssuSoftware.user.oauth.dto;

import ssuSoftware.user.entity.User;
import ssuSoftware.user.entity.kind.Authority;

import java.util.Map;

public class GoogleOAuth2UserAttribute extends OAuth2UserAttribute {

    private static final String GOOGLE_PROVIDER_ID = "google";

    public GoogleOAuth2UserAttribute(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public User toEntity() {
        return User.builder()
                .authority(Authority.USER)
                .provider(GOOGLE_PROVIDER_ID)
                .profilePhotoLink(getProfileImage())
                .name(getNickname())
                .email(getEmail())
                .build();
    }

    @Override
    public String getEmail() {
        return getAttributes().get("email").toString();
    }

    @Override
    public String getNickname() {
        return getAttributes().get("name").toString();
    }

    @Override
    public String getProfileImage() {
        return getAttributes().get("picture").toString();
    }
}
