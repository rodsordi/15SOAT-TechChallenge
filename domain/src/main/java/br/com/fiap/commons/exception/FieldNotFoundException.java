package br.com.fiap.commons.exception;

import static java.lang.String.format;

public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(Class<?> resourceClass, String fieldName) {
        super(format("Field [%s.%s] not found", resourceClass.getSimpleName(), fieldName));
    }
}
