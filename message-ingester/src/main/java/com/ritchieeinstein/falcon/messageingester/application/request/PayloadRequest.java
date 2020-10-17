package com.ritchieeinstein.falcon.messageingester.application.request;

import com.ritchieeinstein.falcon.messageingester.application.validators.TimestampConstraint;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.Transient;

public class PayloadRequest {

    @NotEmpty
    private String content;

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
