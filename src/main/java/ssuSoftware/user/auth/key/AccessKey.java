package gan.missulgan.security.auth.key;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ssuSoftware.user.auth.key.JwtKey;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
//빈등록을 위한
@Qualifier("accessKey")
@Slf4j
public class AccessKey implements JwtKey {

    @Value("${app.auth.accessTokenExpiry}")
    private Long accessTokenDuration;

    private final Key key;
    private final JwtParser parser;

    private AccessKey(@Value("${app.auth.accessTokenSecret}") String accessTokenSecret) {
        this.key = createKey(accessTokenSecret);
        this.parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    //Key 객체는 JWT의 서명(Signature)을 생성하고 검증하는 데 사용될 것으로 예상됩니다.
    Key createKey(String secret) {
        byte[] secretBytes = Base64.getEncoder()
                .encode(secret.getBytes());
        return Keys.hmacShaKeyFor(secretBytes); //key객체로 변환
    }

    //이 코드는 JWT(JSON Web Token) 토큰을 생성하는 Java 메서드
    @Override
    public String getTokenWith(Claims claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(Duration.ofSeconds(accessTokenDuration))))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public Claims parse(String token) {
        return parser.parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean validate(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
        return false;
    }
}
