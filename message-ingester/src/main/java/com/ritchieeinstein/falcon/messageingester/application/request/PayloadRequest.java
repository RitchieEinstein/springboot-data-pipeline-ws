package com.ritchieeinstein.falcon.messageingester.application.request;

import javax.validation.constraints.NotEmpty;

public class PayloadRequest {

    @NotEmpty
    private String content;

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
}
