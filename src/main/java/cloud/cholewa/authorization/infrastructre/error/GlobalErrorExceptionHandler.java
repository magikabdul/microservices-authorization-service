package cloud.cholewa.authorization.infrastructre.error;

import cloud.cholewa.authorization.infrastructre.error.model.Errors;
import cloud.cholewa.authorization.infrastructre.error.processors.DefaultExceptionProcessor;
import cloud.cholewa.authorization.infrastructre.error.processors.ExceptionProcessor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class GlobalErrorExceptionHandler extends AbstractErrorWebExceptionHandler {

    private final Map<Class<? extends Exception>, ExceptionProcessor> processors;
    private final DefaultExceptionProcessor defaultExceptionProcessor = new DefaultExceptionProcessor();

    public GlobalErrorExceptionHandler(
        final ErrorAttributes errorAttributes,
        final WebProperties properties,
        final ApplicationContext applicationContext,
        final ServerCodecConfigurer configurer
    ) {
        super(errorAttributes, properties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());

        processors = Map.ofEntries(
//            Map.entry(ConstraintViolationException.class, new ConstraintViolationExceptionProcessor()),
//            Map.entry(DuplicateKeyException.class, new DuplicateKeyExceptionProcessor()),
//            Map.entry(NotImplementedException.class, new NotImplementedExceptionProcessor()),
//            Map.entry(ServerWebInputException.class, new ServerWebInputExceptionProcessor()),
//            Map.entry(WebClientResponseException.class, new WebClientResponseExceptionProcessor()),
//            Map.entry(WebExchangeBindException.class, new WebExchangeBindExceptionProcessor()),
//            Map.entry(NoSuchElementException.class, new NoSuchElementProcessor())
        );

    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        Throwable throwable = getError(request);

        Errors errors = processors.getOrDefault(throwable.getClass(), defaultExceptionProcessor).apply(throwable);

        return ServerResponse.status(errors.getHttpStatus()).body(BodyInserters.fromValue(errors));
    }

    @Override
    protected void logError(final ServerRequest request, final ServerResponse response, final Throwable throwable) {

    }
}
