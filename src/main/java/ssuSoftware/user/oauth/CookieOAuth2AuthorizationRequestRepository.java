package ssuSoftware.user.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import ssuSoftware.user.oauth.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
@Component
@Primary
@RequiredArgsConstructor
public class CookieOAuth2AuthorizationRequestRepository
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";

    private final CookieUtils cookieUtils;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return cookieUtils.getCookie(request, AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> cookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request,
                                         HttpServletResponse response) {
        if (authorizationRequest == null) {
            removeAuthorizationRequestCookies(request, response);
            return;
        }
        String serializedAuthorizationRequest = cookieUtils.serialize(authorizationRequest);
        cookieUtils.addCookie(response, AUTHORIZATION_REQUEST_COOKIE_NAME, serializedAuthorizationRequest);

        String redirectUri = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        cookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUri);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        OAuth2AuthorizationRequest authorizationRequest = this.loadAuthorizationRequest(request); // 현재 저장되어 있는 OAuth2AuthorizationRequest 객체를 로드
        if (authorizationRequest != null) { // 저장된 객체가 있으면 삭제
            cookieUtils.deleteCookie(request, response, AUTHORIZATION_REQUEST_COOKIE_NAME); // 쿠키 삭제
        }
        return authorizationRequest;
    }

    public Optional<Cookie> getRedirectUriFromCookies(HttpServletRequest request) {
        return cookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.deleteCookie(request, response, AUTHORIZATION_REQUEST_COOKIE_NAME);
        cookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
    }
}