package ssuSoftware.hearHear.core.dto.user;

import lombok.Getter;
import ssuSoftware.hearHear.core.dto.ExceptionPossible;
import ssuSoftware.hearHear.error.handler.code.ErrorCode;

public class AuthResponse {

    public static class JwtWithAuth implements ExceptionPossible {

        private ErrorCode errorCode;

        private String type;

        private String token;

        private String authority;

        private Long userId;

        @Override
        public ErrorCode getErrorCode(){return errorCode;}

        private JwtWithAuth(ErrorCode errorCode){ this.errorCode = errorCode;}

        public static JwtWithAuth authException(ErrorCode errorCode){
            return new JwtWithAuth(errorCode);
        }
    }
}
