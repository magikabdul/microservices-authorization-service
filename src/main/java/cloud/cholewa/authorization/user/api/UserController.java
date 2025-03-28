package cloud.cholewa.authorization.user.api;

import cloud.cholewa.authorization.user.model.User;
import cloud.cholewa.authorization.user.model.UserRegister;
import cloud.cholewa.authorization.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    Mono<ResponseEntity<User>> registerUser(@RequestBody final UserRegister userRegister) {
        return userService.register(userRegister)
            .map(ResponseEntity::ok);
    }

    @GetMapping("login")
    Mono<ResponseEntity<Void>> login() {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build());
    }
}
