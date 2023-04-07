package ssuSoftware.user.service;

import ssuSoftware.hearHear.core.dto.user.UserResponse;

public interface OAuthAdapter {

    //인증 URL을 반환하는 메소드. 사용자를 인증하기 위한 URL을 반환합니다.
    String getAuthorizationUrl();

    //인증 코드를 이용하여 액세스 토큰을 발급받는 메소드입니다. 액세스 토큰은 사용자 인증 후 서비스에서
    //발급되며, 이를 이용하여 사용자 정보를 조회할 수 있습니다.
    String getAccessToken(String code);

    // ^^ 액세스 토큰을 이용하여 사용자 정보를 조회하는 메소드
    UserResponse.Info getUserInfo(String accessToken);
}
