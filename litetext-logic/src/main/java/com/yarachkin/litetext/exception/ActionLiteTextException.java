package com.yarachkin.litetext.exception;

public class ActionLiteTextException extends Exception {
    public ActionLiteTextException() {
    }

    public ActionLiteTextException(String message) {
        super(message);
    }

    public ActionLiteTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionLiteTextException(Throwable cause) {
        super(cause);
    }
}
