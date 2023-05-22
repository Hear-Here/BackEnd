//package ssuSoftware.user.oauth.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ssuSoftware.hearHear.entity.User;
//import ssuSoftware.hearHear.entity.kind.Role;
//import ssuSoftware.hearHear.repository.UserRepository;
//
//import ssuSoftware.user.oauth.OauthService;
//import ssuSoftware.user.oauth.dto.KakaoUserInfo;
//
//
//import java.util.Map;
//import java.util.Set;
//
//@Service
//@RequiredArgsConstructor
//public class ThirdPartyOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    private final UserRepository userRepository;
//
//    @Transactional
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String nameAttribute = oAuth2User.getName();
//        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(attributes);
//        User user = saveOrUpdate(kakaoUserInfo); // DB에 저장
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"),
//                attributes, nameAttribute);
//    }
//
//    private User saveOrUpdate(KakaoUserInfo kakaoUserInfo) {
//        User User = userRepository.findByProviderId(kakaoUserInfo.getId())
//                .map(m -> m.update(kakaoUserInfo.getName(), kakaoUserInfo.getEmail(), kakaoUserInfo.getImageUrl())) // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
//                .orElse(userProfile.toMember());
//        return memberRepository.save(member);
//    }
//
//
//}