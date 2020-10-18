package com.ritchieeinstein.falcon.messageingester.domain.service;

import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.repository.MessageRepository;
import com.ritchieeinstein.falcon.messageingester.infrastructure.streamer.CloudStreamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableBinding(CloudStreamerService.class)
public class MessagePayloadServiceImpl implements MessagePayloadService{

    @Autowired
    private CloudStreamerService cloudStreamerService;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void pushMessageIntoQueue(MessagePayload payload) {
        cloudStreamerService.pushPayload().send(MessageBuilder.withPayload(payload).build());
    }

    @Override
    public List<EnrichedMessageDTO> getAllMessages() {
        List<MessagePayload> messagePayloads = messageRepository.getAllPayloads();
        return messagePayloads.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
    }
}
