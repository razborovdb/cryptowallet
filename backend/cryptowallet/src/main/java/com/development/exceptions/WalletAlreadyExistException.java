package com.development.exceptions;

public class WalletAlreadyExistException extends RuntimeException {


    /**
     * Exception with no message or cause.
     */
    public WalletAlreadyExistException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public WalletAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletAlreadyExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public WalletAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
