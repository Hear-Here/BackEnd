package ssuSoftware.user.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

//    commence 메소드는 AuthenticationEntryPoint 인터페이스에서 정의된 메소드로, 인증이 실패했을 때 호출되는 메소드입니다
//JwtAuthenticationEntryPoint 클래스에서는 인증 실패 시 401 Unauthorized 상태 코드와 JSON 형식의 오류 메시지를 반환하도록 구현하고 있습니다. 이를 통해 클라이언트에서는 인증 실패에 대한 적절한 처리를 할 수 있습니다.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
