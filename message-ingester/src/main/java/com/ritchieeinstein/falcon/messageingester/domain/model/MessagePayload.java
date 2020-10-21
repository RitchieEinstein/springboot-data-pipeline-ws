package com.ritchieeinstein.falcon.messageingester.domain.model;

public class MessagePayload {

    private String content;

    private String timestamp;

    public MessagePayload(String content, String timestamp) {
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

    /**
     * IntelliJ generated Equals and HashCode for better comparisons.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagePayload that = (MessagePayload) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessagePayload{" +
                "content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
