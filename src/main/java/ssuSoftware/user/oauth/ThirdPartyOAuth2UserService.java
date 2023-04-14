package ssuSoftware.user.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ssuSoftware.user.entity.User;
import ssuSoftware.user.oauth.dto.OAuth2UserAttribute;
import ssuSoftware.user.oauth.dto.OAuth2UserAttributeFactory;
import ssuSoftware.user.oauth.dto.OAuthUserImpl;
import ssuSoftware.user.oauth.dto.SavedUserDTO;
import ssuSoftware.user.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ThirdPartyOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserAttribute attributes = OAuth2UserAttributeFactory.of(registrationId, oAuth2User.getAttributes());
        SavedUserDTO savedMemberDTO = save(attributes);
        return new OAuthUserImpl(savedMemberDTO, attributes.getAttributes());
    }
    private SavedUserDTO save(OAuth2UserAttribute userAttribute) {
        String email = userAttribute.getEmail();
        String profileImage = userAttribute.getProfileImage();
        return userRepository.findByEmail(email)
                .map(entity -> {
                    User updatedMember = entity.updateProfileImage(profileImage);
                    return SavedUserDTO.from(updatedMember);
                })
                .orElseGet(() -> {
                    User savedMember = userRepository.save(userAttribute.toEntity());
                    return SavedUserDTO.from(savedMember, true);
                });
    }
}
