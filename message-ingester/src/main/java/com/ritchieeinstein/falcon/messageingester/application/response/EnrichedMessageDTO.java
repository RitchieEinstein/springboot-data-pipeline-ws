package com.ritchieeinstein.falcon.messageingester.application.response;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.utils.PalindromeUtils;

public class EnrichedMessageDTO {

    private String content;
    private String timestamp;
    private long longest_palindrome_size;

    public EnrichedMessageDTO(MessagePayload payload){
        this.content = payload.getContent();
        this.timestamp = payload.getTimestamp();
        calculateLongestPalindromeLength();
    }

    private long calculateLongestPalindromeLength(){
        return new PalindromeUtils().getLongestPalindromeLength(content);
    }
}
