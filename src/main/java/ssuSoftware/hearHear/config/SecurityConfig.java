package ssuSoftware.hearHear.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JwtCon

}
