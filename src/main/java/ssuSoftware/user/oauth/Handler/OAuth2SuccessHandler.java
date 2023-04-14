package ssuSoftware.user.oauth.Handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ssuSoftware.user.auth.JwtTokenProvider;
import ssuSoftware.user.oauth.CookieOAuth2AuthorizationRequestRepository;
import ssuSoftware.user.oauth.dto.OAuthUserImpl;
import ssuSoftware.user.oauth.dto.SavedUserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${frontend.redirectUri}")
    private String redirectionUri;
    @Value("${frontend.authorizedRedirectUris}")
    private String[] authorizedUris;

    private final JwtTokenProvider jwtTokenProvider;
    private final CookieOAuth2AuthorizationRequestRepository requestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String targetUrl = getTargetUrl(request, authentication);
        requestRepository.removeAuthorizationRequestCookies(request, response);
        response.sendRedirect(targetUrl);
    }

    private String getTargetUrl(HttpServletRequest request, Authentication authentication) {
        OAuthUserImpl oAuthUser = (OAuthUserImpl) authentication.getPrincipal();
        SavedUserDTO savedMemberDTO = oAuthUser.getSavedUserDTO();
        String accessToken = jwtTokenProvider.generateAccessToken(savedMemberDTO);
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedMemberDTO);

        Optional<String> redirectUriOptional = requestRepository.getRedirectUriFromCookies(request)
                .map(Cookie::getValue);
        String targetUrl = selectUri(redirectUriOptional);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .queryParam("firstTime", savedMemberDTO.getIsFirstTime())
                .queryParam("name", savedMemberDTO.getName())
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }

    private String selectUri(Optional<String> redirectUriOptional) {
        return redirectUriOptional
                .filter(this::isAuthorizedPattern)
                .orElse(redirectionUri);
    }

    private boolean isAuthorizedPattern(String requestRedirectUri) {
        return Arrays.stream(authorizedUris)
                .anyMatch(authorizedUri -> Pattern.matches(authorizedUri, requestRedirectUri));
    }
}