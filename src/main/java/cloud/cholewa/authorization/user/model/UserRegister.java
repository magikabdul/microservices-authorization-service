package cloud.cholewa.authorization.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRegister {
    private String username;
    private String password;
    private String email;
    private Role role;
}
