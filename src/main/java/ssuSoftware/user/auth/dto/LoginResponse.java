package ssuSoftware.user.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.entity.kind.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private Long id;
    private Role role;
    private String accessToken;
    private String refreshToken;
    private AuthState authState;

    public LoginResponse(User user, String accessToken, String refreshToken,AuthState authState){
        this.id = user.getId();
        this.role = user.getRole();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.authState = authState;
    }
}