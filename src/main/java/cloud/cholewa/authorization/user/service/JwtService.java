package cloud.cholewa.authorization.user.service;

import cloud.cholewa.authorization.config.TokenConfiguration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final TokenConfiguration tokenConfiguration;

    public void validateToken(final String token) {
        Jwts.parser()
            .decryptWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenConfiguration.getSecret())))
            .build()
            .parse(token);
    }

    public String generateToken(final String username) {
        Map<String, String> claims = Map.of("username", username);
        return Jwts.builder()
            .claims(claims)
            .signWith(Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(tokenConfiguration.getSecret())))
            .compact();
    }
}
