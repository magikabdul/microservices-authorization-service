package cloud.cholewa.authorization.user.api;

import cloud.cholewa.authorization.user.model.User;
import cloud.cholewa.authorization.user.model.UserLogin;
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

    @PostMapping("register")
    Mono<ResponseEntity<User>> registerUser(@RequestBody final UserRegister userRegister) {
        return userService.register(userRegister)
            .map(ResponseEntity::ok);
    }

    @PostMapping("login")
    Mono<ResponseEntity<String>> login(@RequestBody final UserLogin userLogin) {
        return userService.login(userLogin)
            .map(ResponseEntity::ok)
            .doOnError(throwable -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    Mono<ResponseEntity<Void>> test() {
        return Mono.just(ResponseEntity.ok().build());
    }
}
