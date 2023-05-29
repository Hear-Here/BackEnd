package ssuSoftware.hearHear.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ssuSoftware.hearHear.entity.User;
import ssuSoftware.hearHear.repository.UserRepository;

import java.util.Optional;

@Getter
@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    private final UserRepository userRepository;
    public User getUser(){

        String userEmail = (String)authentication.getPrincipal();
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalStateException("User not found"));
        return user;
    }
}
