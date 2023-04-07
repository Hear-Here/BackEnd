package ssuSoftware.hearHear.core.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class AuthRequest {

    @Setter
    @Getter
    @AllArgsConstructor
    public static class JwtWithAuth{

        private final String email;

        private final String oauthId;

    }
}
