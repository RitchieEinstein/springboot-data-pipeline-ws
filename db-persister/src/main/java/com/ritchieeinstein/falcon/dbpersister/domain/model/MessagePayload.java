package com.ritchieeinstein.falcon.dbpersister.domain.model;

import java.util.UUID;

public class MessagePayload {

    private UUID id;
    private String content;
    private String timestamp;

    public MessagePayload(String content, String timestamp) {
        this.content = content;
        this.timestamp = timestamp;
        this.id = UUID.randomUUID();
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
