package io.filecoin.crypto.exception;

public class AddressFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AddressFormatException() {
    }

    public AddressFormatException(String s) {
        super(s);
    }

    public AddressFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressFormatException(Throwable cause) {
        super(cause);
    }

}
