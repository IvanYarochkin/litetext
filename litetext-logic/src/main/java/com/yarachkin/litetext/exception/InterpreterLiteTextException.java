package com.yarachkin.litetext.exception;

public class InterpreterLiteTextException extends Exception {
    public InterpreterLiteTextException() {
    }

    public InterpreterLiteTextException(String message) {
        super(message);
    }

    public InterpreterLiteTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterpreterLiteTextException(Throwable cause) {
        super(cause);
    }
}
