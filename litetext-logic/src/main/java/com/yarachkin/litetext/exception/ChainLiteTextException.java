package com.yarachkin.litetext.exception;

public class ChainLiteTextException extends Exception {
    public ChainLiteTextException() {
    }

    public ChainLiteTextException(String message) {
        super(message);
    }

    public ChainLiteTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChainLiteTextException(Throwable cause) {
        super(cause);
    }
}
