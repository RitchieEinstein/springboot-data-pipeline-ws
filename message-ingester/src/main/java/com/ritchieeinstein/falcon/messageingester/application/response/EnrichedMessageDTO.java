package com.ritchieeinstein.falcon.messageingester.application.response;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.utils.PalindromeUtils;

public class EnrichedMessageDTO {

    private String content;
    private String timestamp;
    private long longestPalindromeSize;

    public EnrichedMessageDTO(MessagePayload payload){
        this.content = payload.getContent();
        this.timestamp = payload.getTimestamp();
        this.longestPalindromeSize = new PalindromeUtils().getLongestPalindromeLength(content);
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

    public long getLongestPalindromeSize() {
        return longestPalindromeSize;
    }

    public void setLongestPalindromeSize(long longestPalindromeSize) {
        this.longestPalindromeSize = longestPalindromeSize;
    }
}
