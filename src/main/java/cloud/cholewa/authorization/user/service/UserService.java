package cloud.cholewa.authorization.user.service;

import cloud.cholewa.authorization.user.model.Role;
import cloud.cholewa.authorization.user.model.User;
import cloud.cholewa.authorization.user.model.UserRegister;
import cloud.cholewa.authorization.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Mono<User> register(final UserRegister userRegister) {
        User user = User.builder()
            .username(userRegister.getUsername())
            .password(userRegister.getPassword())
            .email(userRegister.getEmail())
            .role(userRegister.getRole() != null ? userRegister.getRole() : Role.USER)
            .build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.findByUsername(user.getUsername())
            .flatMap(userExists -> Mono.error(new RuntimeException("User already exists")))
            .switchIfEmpty(userRepository.save(user))
            .then(Mono.just(user));
    }
}
