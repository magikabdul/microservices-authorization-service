package cloud.cholewa.authorization.user.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@NoArgsConstructor
@Table(name = "users")

public class User implements UserDetails {

    @Id
    private long id;
    private UUID uuid;
    private String email;
    private String username;
    private String password;
    private Role role;
    private boolean isEnabled;
    private boolean isLocked;

    public User(
        final long id,
        final UUID uuid,
        final String email,
        final String username,
        final String password,
        final Role role,
        final boolean isEnabled,
        final boolean isLocked
    ) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
        generateUUID();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    private void generateUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
