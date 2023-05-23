package ssuSoftware.user.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.entity.kind.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private AuthState authState;

    public LoginResponse( String accessToken, String refreshToken,AuthState authState){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.authState = authState;
    }
}