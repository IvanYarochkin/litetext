package com.yarachkin.litetext.exception;

public class CompositeLiteTextException extends Exception {
    public CompositeLiteTextException() {
    }

    public CompositeLiteTextException(String message) {
        super(message);
    }

    public CompositeLiteTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompositeLiteTextException(Throwable cause) {
        super(cause);
    }
}
