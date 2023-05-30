package ssuSoftware.user.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ssuSoftware.hearHear.entity.kind.Role;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Slf4j
@Component
@NoArgsConstructor
public class JwtTokenProvider {


    @Value("${app.auth.accessTokenExpiry}")
    private  long accessTokenDuration;
    @Value("${app.auth.refreshTokenExpiry}")
    private  long refreshTokenDuration;
    @Value("${app.auth.accessTokenSecret}")
    private  String secretKey;

    private Key key;


    @PostConstruct
    public void createKey() {
        byte[] secretBytes = Base64.getEncoder()
                .encode(secretKey.getBytes());
         key = Keys.hmacShaKeyFor(secretBytes);
    }

    public String generateAccessToken(String payload){
        return createToken(payload, accessTokenDuration);
    }


        public String generateRefreshToken(){
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);
            return createToken(generatedString, refreshTokenDuration);
        }

        public String createToken(String payload, long expireLength){
            Claims claims = Jwts.claims().setSubject(payload);
            Date now = new Date();
            Date validity = new Date(now.getTime() + expireLength);
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .signWith(key,SignatureAlgorithm.HS512)
                    .compact();
        }

        public String getPayload(String token){
            try{
                return Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
            }catch (ExpiredJwtException e){
                return e.getClaims().getSubject();
            }catch (JwtException e){
                throw new RuntimeException("유효하지 않은 토큰입니다.");
            }
        }

        public boolean validateToken(String token){
            try{
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token);
                return !claimsJws.getBody().getExpiration().before(new Date());
            }catch (JwtException | IllegalArgumentException exception){
                return false;
            }
        }


    public Authentication getAuthentication(String accessToken) {
        String email = getPayload(accessToken);
        Collection<? extends GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(Role.USER.toString()));
        return new UsernamePasswordAuthenticationToken(email, "", authorities);
    }


}

