package io.filecoin.crypto.exception;

public class CBORException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CBORException() {
    }

    public CBORException(String s) {
        super(s);
    }

    public CBORException(String message, Throwable cause) {
        super(message, cause);
    }

    public CBORException(Throwable cause) {
        super(cause);
    }
}
