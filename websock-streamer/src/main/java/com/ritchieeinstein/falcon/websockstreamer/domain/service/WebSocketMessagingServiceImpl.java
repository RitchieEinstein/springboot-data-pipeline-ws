package com.ritchieeinstein.falcon.websockstreamer.domain.service;

import com.ritchieeinstein.falcon.websockstreamer.domain.model.MessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Payload;

/**
 * WebSocket Communication is achieved using the SimpMessaging Template.
 */
@Service
public class WebSocketMessagingServiceImpl implements WebSocketMessageService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketMessagingServiceImpl.class);

    private SimpMessagingTemplate brokerMessagingTemplate;

    public WebSocketMessagingServiceImpl(SimpMessagingTemplate brokerMessagingTemplate) {
        this.brokerMessagingTemplate = brokerMessagingTemplate;
    }

    /**
     * Application will be broadcasting at the topic "websock-relay"
     * @param payload
     */
    @Override
    public void pushSocketFrame(MessagePayload payload) {
        if(null == payload) throw new NullPointerException("Payload should not be null");
        brokerMessagingTemplate.convertAndSend("/topic/websock-relay",payload);
    }
}
