package ssuSoftware.user.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssuSoftware.user.auth.JwtTokenProvider;
import ssuSoftware.user.auth.dto.AuthState;
import ssuSoftware.user.auth.dto.LoginResponse;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.entity.kind.Role;
import ssuSoftware.user.oauth.dto.KakaoUserInfo;
import ssuSoftware.hearHear.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OauthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public LoginResponse loginWithToken(String providerName, KakaoUserInfo kakaoUserInfo) {
        if(!providerName.equalsIgnoreCase("kakao")){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        if(userRepository.existsByProviderId(kakaoUserInfo.getId())){
            User user = userRepository.findByProviderId(kakaoUserInfo.getId()).orElseThrow();

            String accessToken = jwtTokenProvider.generateAccessToken(String.valueOf(user.getEmail()));
            String refreshToken = jwtTokenProvider.generateRefreshToken();
            return new LoginResponse( accessToken, refreshToken, AuthState.Login);

        } else {

            User user = User.builder()
                    .providerId(kakaoUserInfo.getId())
                    .email(kakaoUserInfo.getEmail())
                    .role(Role.USER)
                    .build();

            String accessToken = jwtTokenProvider.generateAccessToken(String.valueOf(user.getEmail()));
            String refreshToken = jwtTokenProvider.generateRefreshToken();

            return new LoginResponse(accessToken, refreshToken, AuthState.Join);
        }
        }



}