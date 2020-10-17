package com.ritchieeinstein.falcon.messageingester.domain.service;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;


public interface MessagePayloadService {

    void pushMessageIntoQueue(MessagePayload payload);
}
