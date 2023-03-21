package play.go.entity.enumed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String role;
}
