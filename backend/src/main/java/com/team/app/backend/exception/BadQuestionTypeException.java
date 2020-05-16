package com.team.app.backend.exception;

public class BadQuestionTypeException extends Exception {

    public BadQuestionTypeException() {
    }

    public BadQuestionTypeException(String message) {
        super(message);
    }

    public BadQuestionTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadQuestionTypeException(Throwable cause) {
        super(cause);
    }

    public BadQuestionTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
