package com.ritchieeinstein.falcon.messageingester.application.response;

public class EnrichedMessageDTO {

    private String content;
    private String timestamp;
    private long longestPalindromeSize;

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

    public long getLongestPalindromeSize() {
        return longestPalindromeSize;
    }

    public void setLongestPalindromeSize(long longestPalindromeSize) {
        this.longestPalindromeSize = longestPalindromeSize;
    }
}
