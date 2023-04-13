package ssuSoftware.user.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ssuSoftware.user.auth.dto.AuthUserDTO;
import ssuSoftware.user.auth.dto.TokenResponseDto;
import ssuSoftware.user.auth.key.JwtKey;
import ssuSoftware.user.oauth.dto.SavedUserDTO;
import ssuSoftware.user.repository.UserRepository;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String ROLE_KEY = "role";

    private static final String ID_KEY = "id";

    private final UserRepository userRepository;

    private final JwtKey accessKey;

    private final JwtKey refreshKey;

    public String generateAccessToken(SavedUserDTO savedUserDTO) {
        Claims claims = buildClaims(savedUserDTO);
        return accessKey.getTokenWith(claims);
    }
    public String generateRefreshToken(SavedUserDTO savedUserDTO) {
        Claims claims = buildClaims(savedUserDTO);
        return refreshKey.getTokenWith(claims);
    }
    private Claims buildClaims(SavedUserDTO savedMemberDTO) {
        Long id = savedMemberDTO.getId();
        String email = savedMemberDTO.getEmail();
        String role = savedMemberDTO.getRole();

        Claims claims = Jwts.claims();
        claims.setSubject(email);
        claims.put(ID_KEY, id.toString());
        claims.put(ROLE_KEY, role);
        return claims;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = accessKey.parse(accessToken);
        String email = claims.getSubject();
        String id = (String) (claims.get(ID_KEY));
        String role = (String) claims.get(ROLE_KEY);
        Long idLong = getIdLong(email, id); // TODO: 쓰레기 제거

        Collection<? extends GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(role));
        AuthUserDTO authMemberDTO = AuthUserDTO.builder()
                .id(idLong)
                .email(email)
                .role(role)
                .build();
        return new UsernamePasswordAuthenticationToken(authMemberDTO, "", authorities);
    }

    @Deprecated
    private Long getIdLong(String email, String id) {
        Long idLong;
        if (id == null) {
            idLong = getUser(email).getId();
        } else {
            idLong = Long.parseLong(id);
        }
        return idLong;
    }

    public TokenResponseDto renew(String refreshToken) {
        Claims claims = refreshKey.parse(refreshToken);
        String email = claims.getSubject();
        SavedUserDTO savedUserDTO = getUser(email);
        String accessToken = generateAccessToken(savedUserDTO);
        return new TokenResponseDto(accessToken);
    }

    public boolean validate(String accessToken) {
        return accessKey.validate(accessToken);
    }

    private SavedUserDTO getUser(String email) {
        return userRepository.findByEmail(email)
                .map(SavedUserDTO::from)
                .orElseThrow(NoSuchElementException::new);
    }
}
