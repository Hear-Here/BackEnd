package ssuSoftware.user.auth.key;

import io.jsonwebtoken.Claims;

public interface JwtKey {

    String getTokenWith(Claims caims);

    Claims parse(String token);

    boolean validate(String token);
}
