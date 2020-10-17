package com.ritchieeinstein.falcon.messageingester.domain.service;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.infrastructure.streamer.CloudStreamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(CloudStreamerService.class)
public class MessagePayloadServiceImpl implements MessagePayloadService{

    @Autowired
    private CloudStreamerService cloudStreamerService;

    @Override
    public void pushMessageIntoQueue(MessagePayload payload) {
        cloudStreamerService.pushPayload().send(MessageBuilder.withPayload(payload).build());
    }
}
