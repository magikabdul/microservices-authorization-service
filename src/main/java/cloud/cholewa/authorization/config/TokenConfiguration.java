package cloud.cholewa.authorization.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TokenConfiguration {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration.time}")
    private String expirationTime;

    @Value("${jwt.token.expiration.refresh}")
    private String expirationRefresh;
}
