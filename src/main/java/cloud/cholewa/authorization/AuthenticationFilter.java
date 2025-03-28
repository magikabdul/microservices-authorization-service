package cloud.cholewa.authorization;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class AuthenticationFilter implements WebFilter {

    private static final List<String> PATHS_TO_SKIP = List.of("/register", "/login");

    @Override
    @Nonnull
    public Mono<Void> filter(@Nonnull final ServerWebExchange exchange, @Nonnull final WebFilterChain chain) {
        log.info("AuthenticationFilter is active - testing path: {}", exchange.getRequest().getPath());
        if (skipAuthorisation(exchange)) {
            log.info("Path {} is skipped", exchange.getRequest().getPath());
        } else {
            throw new AuthenticationServiceException("Authorisation is required");
        }
        return chain.filter(exchange);
    }

    private boolean skipAuthorisation(final ServerWebExchange exchange) {
        return PATHS_TO_SKIP.stream().anyMatch(exchange.getRequest().getPath().value()::contains);
    }
}
