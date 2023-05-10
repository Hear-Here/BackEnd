package ssuSoftware.user.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import ssuSoftware.user.auth.JwtTokenProvider;
import ssuSoftware.user.auth.dto.AuthState;
import ssuSoftware.user.auth.dto.LoginResponse;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.entity.kind.Role;
import ssuSoftware.user.oauth.dto.KakaoUserInfo;
import ssuSoftware.user.oauth.dto.UserToken;
import ssuSoftware.hearHear.repository.UserRepository;



@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OauthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public LoginResponse loginWithToken(String providerName, UserToken userToken) {
        if(!providerName.equalsIgnoreCase("kakao")){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        //애트리뷰트에서 정보 뽑기
        KakaoUserInfo kakaoUserInfo = getUserAttributesByToken(userToken);

        if(userRepository.existsByProviderId(kakaoUserInfo.getId())){
            User user = userRepository.findByProviderId(kakaoUserInfo.getId()).orElseThrow();

            String accessToken = jwtTokenProvider.generateAccessToken(String.valueOf(user.getId()));
            String refreshToken = jwtTokenProvider.generateRefreshToken();
            return new LoginResponse(user, accessToken, refreshToken, AuthState.Join);
        }

        else {
            User user = User.builder()
                    .providerId(kakaoUserInfo.getId())
                    .name(kakaoUserInfo.getKakao_account().getName())
                    .role(Role.USER)
                    .nickName(kakaoUserInfo.getKakao_account().getProfile().getNickname())
                    .kakaoAccessToken(userToken.getAccessToken())
                    .kakaoRefreshToken(userToken.getRefreshToken())
                    .build();

            String accessToken = jwtTokenProvider.generateAccessToken(String.valueOf(user.getId()));
            String refreshToken = jwtTokenProvider.generateRefreshToken();

            return new LoginResponse(user, accessToken, refreshToken, AuthState.Login);
        }
        }



    private KakaoUserInfo getUserAttributesByToken(UserToken userToken){
        KakaoUserInfo kakaoUserInfo = WebClient.create()
                .get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(userToken.getAccessToken()))
                .retrieve()
                .bodyToMono(KakaoUserInfo.class)
                .block();

       return kakaoUserInfo;
    }


}