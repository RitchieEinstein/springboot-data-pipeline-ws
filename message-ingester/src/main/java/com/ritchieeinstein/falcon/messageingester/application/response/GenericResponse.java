package com.ritchieeinstein.falcon.messageingester.application.response;

/**
 * The Default Generic Response for the Requests placed.
 * @param <T> will be the incoming request.
 */

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
