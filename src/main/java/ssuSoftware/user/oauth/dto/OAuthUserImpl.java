package ssuSoftware.user.oauth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class OAuthUserImpl implements OAuth2User {

    private final SavedUserDTO savedUserDTO;
    private final Map<String, Object> attributes;

    public OAuthUserImpl(SavedUserDTO savedMemberDTO, Map<String, Object> attributes) {
        this.savedUserDTO = savedMemberDTO;
        this.attributes = attributes;
    }

    public SavedUserDTO getSavedUserDTO() {
        return savedUserDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(savedUserDTO.getRole()));
    }

    @Override
    public String getName() {
        return savedUserDTO.getEmail();
    }
}