package ssuSoftware.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import ssuSoftware.user.auth.JwtAuthenticationEntryPoint;
import ssuSoftware.user.auth.JwtAuthenticationFilter;
import ssuSoftware.user.auth.JwtTokenProvider;
import ssuSoftware.user.oauth.CookieOAuth2AuthorizationRequestRepository;
import ssuSoftware.user.oauth.Handler.OAuth2FailureHandler;
import ssuSoftware.user.oauth.Handler.OAuth2SuccessHandler;
import ssuSoftware.user.oauth.ThirdPartyOAuth2UserService;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ThirdPartyOAuth2UserService thirdPartyOAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final OAuth2FailureHandler failureHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final CookieOAuth2AuthorizationRequestRepository oAuth2AuthorizationRequestRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 생성 정책을 STATELESS로 설정하여 세션을 사용하지 않도록 합니다.
                .and()

                .formLogin().disable()
                .httpBasic().disable()

                .authorizeRequests()
                .antMatchers("/docs", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs").permitAll()
                .antMatchers("/auth/renew").permitAll()
                .mvcMatchers(GET, "/image/{fileName}").permitAll()
                .mvcMatchers(GET, "/tag/all").permitAll()
                .mvcMatchers(GET, "/drawing/{memberId}").permitAll()
                .mvcMatchers(GET, "/drawing/member/{memberId}").permitAll()
                .mvcMatchers("/drawing/heart/**").permitAll()
                .mvcMatchers("/drawing/random/**").permitAll()
                .mvcMatchers(GET, "/heart/{drawingId}", "/scrap/{drawingId}").permitAll()
                .mvcMatchers(GET, "/member/{memberId}").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()
                .and()

                .exceptionHandling() //스프링 시큐리티구성에서 예외처리 설정하는 부분
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증되지 않은 사용자가 접근할 때, jwtAuthenticationEntryPoint에서 정의한 로직이 실행되어 사용자를 인증하도록 유도하거나 에러 메시지를 반환할 수 있습니다.
                .accessDeniedHandler(accessDeniedHandler)//인가되지 않은 사용자 접근 거부
                .and()

                .oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(oAuth2AuthorizationRequestRepository) //OAuth2AuthorizationRequestRepository는 인가 요청 정보를 저장하고 관리하는 인터페이스로, 스프링 시큐리티에서 기본적으로 제공하는
                .and()
                .userInfoEndpoint()
                .userService(thirdPartyOAuth2UserService)
                .and()
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
