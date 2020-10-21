package com.ritchieeinstein.falcon.websockstreamer.domain.service;

import com.ritchieeinstein.falcon.websockstreamer.domain.model.MessagePayload;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WebSocketMessagingServiceImplTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    WebSocketMessageService webSocketMessageService;

    @BeforeEach
    void setUp(){
        webSocketMessageService = new WebSocketMessagingServiceImpl(simpMessagingTemplate);
    }

    @Test
    void pushSocketFrame_successCondition() {
        webSocketMessageService.pushSocketFrame(new MessagePayload("",""));
        Mockito.verify(simpMessagingTemplate, Mockito.times(1)).convertAndSend(ArgumentMatchers.anyString(),ArgumentMatchers.any(MessagePayload.class));
    }

    @Test
    void pushSocketFrame_nullValuePassed() {
        Assertions.assertThatThrownBy(()->{
           webSocketMessageService.pushSocketFrame(null);
        }).isInstanceOf(NullPointerException.class).hasMessage("Payload should not be null");
    }
}