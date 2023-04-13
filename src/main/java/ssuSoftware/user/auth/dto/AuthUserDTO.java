package ssuSoftware.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuthUserDTO {

    private final Long id;

    private final String email;

    private final String role;

}
