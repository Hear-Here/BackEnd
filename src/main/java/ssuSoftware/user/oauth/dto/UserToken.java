package ssuSoftware.user.oauth.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserToken {

    private String tokenType;
    private String accessToken;
    private int expiresIn;
    private String refreshToken;
    private String scope;
    private int refreshTokenExpiresIn;
}