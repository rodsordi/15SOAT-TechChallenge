package br.com.fiap.commons.domain.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> entityClassNotFound) {
        super(format("Resource [%s] not found", entityClassNotFound.getSimpleName()));
    }

    public NotFoundException(Class<?> entityClassNotFound, String fieldName, Object fieldValue) {
        super(format("Resource [%s] with [%s]: [%s] not found", entityClassNotFound.getSimpleName(), fieldName, fieldValue));
    }
}
