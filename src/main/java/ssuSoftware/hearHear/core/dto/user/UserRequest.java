package ssuSoftware.hearHear.core.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Modify{
        private String name;

        private String nickname;

        private String profilePhoto;

        private String userLink;
    }
}
