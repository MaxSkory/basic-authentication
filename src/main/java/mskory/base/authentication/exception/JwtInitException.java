package mskory.base.authentication.exception;

public class JwtInitException extends RuntimeException {
    public JwtInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
