//package ssuSoftware.user.oauth.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ssuSoftware.user.entity.User;
//import ssuSoftware.user.oauth.dto.KakaoUserInfo;
//import ssuSoftware.user.repository.UserRepository;
//
//import java.util.Map;
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
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(attributes);
//        User user = saveOrUpdate(kakaoUserInfo); // DB에 저장
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
//                attributes,
//                userNameAttributeName);
//    }
//
//    private User saveOrUpdate(KakaoUserInfo kakaoUserInfo) {
//        User User = UserRepository.findById(kakaoUserInfo.getId())
//                .map(m -> m.update(kakaoUserInfo.getName(), kakaoUserInfo.getEmail(), kakaoUserInfo.getImageUrl())) // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
//                .orElse(userProfile.toMember());
//        return memberRepository.save(member);
//    }
//
//
//}