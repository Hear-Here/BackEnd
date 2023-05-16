//package ssuSoftware.user.auth.dto;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import ssuSoftware.hearHear.entity.kind.Role;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.Set;
//
//public class OAuthUserImpl implements OAuth2User {
//
//
//
//
//
//    @Override
//    public <A> A getAttribute(String name) {
//        return OAuth2User.super.getAttribute(name);
//    }
//
//    @Override
//    public String getName() {
//        return null;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return null;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Set.of(new SimpleGrantedAuthority(Role.USER.toString()));
//    }
//}
