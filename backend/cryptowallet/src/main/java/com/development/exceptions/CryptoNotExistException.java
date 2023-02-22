package com.development.exceptions;

public class CryptoNotExistException extends RuntimeException {


    /**
     * Exception with no message or cause.
     */
    public CryptoNotExistException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CryptoNotExistException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptoNotExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptoNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
