package com.ritchieeinstein.falcon.messageingester.application.rest.controller;

import com.ritchieeinstein.falcon.messageingester.application.request.PayloadRequest;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.service.MessagePayloadService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayloadControllerTest {

    @Mock
    private MessagePayloadService messagePayloadService;

    private PayloadController controller;

    @BeforeEach()
    void setup(){
        controller = new PayloadController(messagePayloadService);
    }
    @Test
    void ingestMessagePayload() {
        controller.ingestMessagePayload(new PayloadRequest("TestMessage","2019-01-01 22:33:55+0530"));
        Mockito.verify(messagePayloadService,Mockito.times(1)).pushMessageIntoQueue(ArgumentMatchers.any(MessagePayload.class));
    }

    @Test
    void getAllEnrichedMessages() {
        controller.getAllEnrichedMessages();
        Mockito.verify(messagePayloadService, Mockito.times(1)).getAllMessages();
    }

    @Test
    void getMessagesWithPagination() {
        controller.getMessagesWithPagination(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        Mockito.verify(messagePayloadService, Mockito.times(1)).getMessagesWithPagination(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
    }
}