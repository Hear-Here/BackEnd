package ssuSoftware.hearHear.core.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssuSoftware.hearHear.core.dto.ExceptionPossible;
import ssuSoftware.hearHear.error.handler.code.ErrorCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    protected ErrorCode errorCode = ErrorCode.NULL;

    @Getter
    @NoArgsConstructor
    public static class Info extends UserResponse implements ExceptionPossible{

        private Long id;
        private String userId;
        private String provider;
        private String email;
        private String name;
        private String nickname;
        private String profilePhoto;
        private String userLink;
        private String gender;
        private boolean isActive;
        private String authority;
        @Setter
        private Long factoryId;

        public Info(Long id,
                    String userId,
                    String provider,
                    String email,
                    String name,
                    String nickname,
                    String profilePhoto,
                    String userLink,
                    String gender,
                    boolean isActive,
                    String authority) {
            this.id = id;
            this.userId = userId;
            this.provider = provider;
            this.email = email;
            this.name = name;
            this.nickname = nickname;
            this.profilePhoto = profilePhoto;
            this.userLink = userLink;
            this.gender = gender;
            this.isActive = isActive;
            this.authority = authority;
        }
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NicknameVerification extends UserResponse implements ExceptionPossible {

        private boolean verified;

        @Override
        public ErrorCode getErrorCode() {
            return errorCode;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Modify extends UserResponse implements ExceptionPossible {

        private boolean modified;

        @Override
        public ErrorCode getErrorCode() {
            return errorCode;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Validation extends UserResponse implements ExceptionPossible {

        private Long userId;

        @Override
        public ErrorCode getErrorCode() {
            return errorCode;
        }
}
