package com.ritchieeinstein.falcon.messageingester.application.response;

public class GenericResponse<T> {

    private T message;
    private final String status = "ACCEPTED";

    public GenericResponse(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
