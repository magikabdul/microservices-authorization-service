package cloud.cholewa.authorization.infrastructre.error.processors;

import cloud.cholewa.authorization.infrastructre.error.model.ErrorMessage;
import cloud.cholewa.authorization.infrastructre.error.model.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Collections;

@Slf4j
public class DefaultExceptionProcessor implements ExceptionProcessor {
    @Override
    public Errors apply(final Throwable throwable) {

        log.error("Generic exception: {}: {}", throwable.getClass().getSimpleName(), throwable.getMessage());

        return Errors.builder()
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .errors(Collections.singleton(
                ErrorMessage.builder()
                    .details(throwable.getClass().getSimpleName())
                    .message("Unhandled error, update processor configuration")
                    .build()
            ))
            .build();
    }
}
