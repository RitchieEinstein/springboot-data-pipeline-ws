package com.ritchieeinstein.falcon.messageingester.domain.model;

public class MessagePayloadVO {

    private String content;
    private String timestamp;
    private long longest_palindrome_size;

    public MessagePayloadVO(MessagePayload payload){
        this.content = payload.getContent();
        this.timestamp = payload.getTimestamp();
    }

    private long calculateLongestPalindromeLength(){
        return 0;
    }
}
