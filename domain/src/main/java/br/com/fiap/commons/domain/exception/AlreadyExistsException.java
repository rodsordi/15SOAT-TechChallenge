package br.com.fiap.commons.domain.exception;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public class AlreadyExistsException extends RuntimeException {

    private final Class<?> clazz;

    private final String id;

    public AlreadyExistsException(Class<?> clazz) {
        super(buildMessage(clazz, null, null));
        this.clazz = clazz;
        id = null;
    }

    public AlreadyExistsException(Class<?> clazz, String fieldName, String fieldValue) {
        super(buildMessage(clazz, fieldName, fieldValue));
        this.clazz = clazz;
        id = fieldValue;
    }

    private static String buildMessage(Class<?> clazz, String fieldName, String fieldValue) {
        var field = "";
        if (fieldName != null && fieldValue != null)
            field = format(" with %s: %s", fieldName, fieldValue);
        return format("%s already exists%s", clazz.getSimpleName(), field);
    }
}
