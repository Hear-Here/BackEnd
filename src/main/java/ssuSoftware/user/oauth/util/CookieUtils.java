package ssuSoftware.user.oauth.util;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component
public class CookieUtils {

    private static final int MAX_AGE = 180;
    private static final int EXPIRED_AGE = 0;

    //HttpServletRequest에서 특정 이름의 쿠키를 찾아서 Optional<Cookie> 형태로 반환합니다.
    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst();
    }

    public void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");  //이는 웹 애플리케이션의 모든 경로에서 해당 쿠키를 사용할 수 있도록 합니다.
        cookie.setHttpOnly(true); //쿠키를 JavaScript로 접근하지 못하도록 설정합니다. 이는 보안을 강화하기 위한 설정입니다.
        cookie.setMaxAge(MAX_AGE); //쿠키의 유효 시간을 설정
        response.addCookie(cookie);
    }

    public void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .forEach(cookie -> setCookieExpired(response, cookie));
    }

    //주어진 객체(Object)를 직렬화하여 Base64로 인코딩
    // 객체의 상태를 바이트 형태로 변환하여 저장하거나 네트워크를 통해 전송할 수 있게 하는 과정
    public String serialize(Object object) {
        byte[] serialized = SerializationUtils.serialize(object);
        return encodeToBase64(serialized); //Base64 인코딩은 이진 데이터를 64개의 문자로 이루어진 텍스트 데이터로 변환하는 과정을 말합니
    }

    //쿠키를 객체로 만드는
    //Base64로 인코딩 된 것을 디코딩 하여 이진 데이터로 변환
    //역직렬화 작업후 원하는 클래스 타입으로 캐스팅하여 반환
    public <T> T deserialize(Cookie cookie, Class<T> castClass) {
        byte[] decoded = decodeFromBase64(cookie.getValue());
        Object deserialized = SerializationUtils.deserialize(decoded);
        return castClass.cast(deserialized);
    }

    private void setCookieExpired(HttpServletResponse response, Cookie cookie) {
        cookie.setValue(null);
        cookie.setPath("/");
        cookie.setMaxAge(EXPIRED_AGE);
        response.addCookie(cookie);
    }

    private String encodeToBase64(byte[] bytes) {
        return Base64.getUrlEncoder()
                .encodeToString(bytes);
    }

    private byte[] decodeFromBase64(String encoded) {
        return Base64.getUrlDecoder()
                .decode(encoded);
    }
}
