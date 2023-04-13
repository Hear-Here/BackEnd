package ssuSoftware.user.oauth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ssuSoftware.user.entity.User;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@Builder
public class SavedUserDTO {

    private final Long id;
    private final String email;
    private final String role;
    private final String name;
    private final Boolean isFirstTime;


    public static SavedUserDTO from(User user) {
        return SavedUserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getAuthority().getRole())
                .name(user.getName())
                .isFirstTime(false)
                .build();
    }

    public static SavedUserDTO from(User user, Boolean isFirstTime) {
        return SavedUserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getAuthority().getRole())
                .name(user.getName())
                .isFirstTime(isFirstTime)
                .build();
    }
}
