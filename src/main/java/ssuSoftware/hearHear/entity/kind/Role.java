package ssuSoftware.hearHear.entity.kind;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), ANOYMOUS("ROLE_ANOYMOUS");

    private String role;
}
