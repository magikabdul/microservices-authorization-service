package cloud.cholewa.authorization.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TokenConfiguration {

    @Value("${jwt.secret}")
    private String secret;
}
