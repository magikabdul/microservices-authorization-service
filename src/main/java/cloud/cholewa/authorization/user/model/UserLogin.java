package cloud.cholewa.authorization.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLogin {
    private String username;
    private String password;
}
