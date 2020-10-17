package com.ritchieeinstein.falcon.websockstreamer.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.validation.Payload;

public class WebSocketMessagingServiceImpl implements WebSocketMessageService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketMessagingServiceImpl.class);

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Override
    public void pushSocketFrame(Payload payload) {
        brokerMessagingTemplate.convertAndSend("/topic/websock-relay",payload);
    }
}
