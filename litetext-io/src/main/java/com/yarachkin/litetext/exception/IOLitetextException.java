package com.yarachkin.litetext.exception;

public class IOLitetextException extends Exception {
    public IOLitetextException() {
    }

    public IOLitetextException(String message) {
        super(message);
    }

    public IOLitetextException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOLitetextException(Throwable cause) {
        super(cause);
    }
}
