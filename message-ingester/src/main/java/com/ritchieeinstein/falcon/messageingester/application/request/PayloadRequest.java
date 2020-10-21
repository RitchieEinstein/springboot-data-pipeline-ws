package com.ritchieeinstein.falcon.messageingester.application.request;

import com.ritchieeinstein.falcon.messageingester.application.validators.TimestampConstraint;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.Transient;

/**
 * The Incoming Message Payload's DTO with field level constriants implemented for validations.
 */

public class PayloadRequest {

    @NotEmpty
    private String content;

    /**
     * The Timestamp given as a string needs to be validated for the factual nature of it. The Timestamp annotation will be perform a custom validation over it.
     */
    @TimestampConstraint
    private String timestamp;

    public PayloadRequest(String content, String timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Transient
    public MessagePayload getMessagePayload(){
        return new MessagePayload(content, timestamp);
    }
}
