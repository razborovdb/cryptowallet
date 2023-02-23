package com.development.exceptions;

public class WalletNotExistException extends RuntimeException {


    /**
     * Exception with no message or cause.
     */
    public WalletNotExistException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public WalletNotExistException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletNotExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
