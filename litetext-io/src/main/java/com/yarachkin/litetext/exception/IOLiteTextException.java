package com.yarachkin.litetext.exception;

public class IOLiteTextException extends Exception {
    public IOLiteTextException() {
    }

    public IOLiteTextException(String message) {
        super(message);
    }

    public IOLiteTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOLiteTextException(Throwable cause) {
        super(cause);
    }
}
