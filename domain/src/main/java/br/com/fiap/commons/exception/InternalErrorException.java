package br.com.fiap.commons.exception;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(Throwable e) {
        super(e);
    }

    public InternalErrorException(String message, Throwable e) {
        super(message, e);
    }
}
