package br.com.fiap.commons.exception;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> resourceClass) {
        super(format("Resource [%s] not found", resourceClass.getSimpleName()));
    }

    public ResourceNotFoundException(Class<?> resourceClass, String fieldName, Object fieldValue) {
        super(format("Resource [%s] with [%s]: [%s] not found", resourceClass.getSimpleName(), fieldName, fieldValue));
    }
}
