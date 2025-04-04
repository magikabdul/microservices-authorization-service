package cloud.cholewa.authorization.infrastructre.error.processors;

import cloud.cholewa.authorization.infrastructre.error.model.Errors;

@FunctionalInterface
public interface ExceptionProcessor {

    Errors apply(final Throwable throwable);
}
