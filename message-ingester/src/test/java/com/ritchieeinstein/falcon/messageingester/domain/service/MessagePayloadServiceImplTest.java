package com.ritchieeinstein.falcon.messageingester.domain.service;

import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.repository.MessageRepository;
import com.ritchieeinstein.falcon.messageingester.infrastructure.streamer.CloudStreamerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessagePayloadServiceImplTest {

    @Mock
    private CloudStreamerService cloudStreamerService;

    @Mock
    private MessageRepository messageRepository;

    private MessagePayloadServiceImpl service;

    @BeforeEach
    void setup(){
        service = new MessagePayloadServiceImpl(cloudStreamerService, messageRepository);
    }

//    @Test
//    void pushMessageIntoQueue_checkInternalCalls() {
//        service.pushMessageIntoQueue(new MessagePayload("amma","2019-01-02 02:44:54-0200"));
//        Mockito.verify(cloudStreamerService, Mockito.times(1)).pushPayload();
//    }

    @Test
    void getAllMessages_baseCheckCalls() {
        List<EnrichedMessageDTO> receivedPayloadList = service.getAllMessages();
        Mockito.verify(messageRepository, Mockito.times(1)).getAllPayloads();
    }

    @Test
    void getAllMessages_responseCheckTest(){
        List<MessagePayload> payloadList = Arrays.asList(
                new MessagePayload("Admin","2019-01-02 02:44:54-0200"),
                new MessagePayload("Amma","2019-01-02 02:44:54-0200"),
                new MessagePayload("malayalam","2019-01-02 02:44:54-0200"),
                new MessagePayload("123321","2019-01-02 02:44:54-0200"));
        Mockito.when(messageRepository.getAllPayloads()).thenReturn(payloadList);
        List<EnrichedMessageDTO> receivedResult = service.getAllMessages();
        Assertions.assertThat(receivedResult).isNotEmpty();
        Assertions.assertThat(receivedResult).first().hasFieldOrPropertyWithValue("longestPalindromeSize",1L);
        Assertions.assertThat(receivedResult).element(1).hasFieldOrPropertyWithValue("longestPalindromeSize",4L);
        Assertions.assertThat(receivedResult).last().hasFieldOrPropertyWithValue("longestPalindromeSize",0L);
    }

    @Test
    void getAllMessagesWithPagination_baseCheckCalls() {
        service.getMessagesWithPagination(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        Mockito.verify(messageRepository, Mockito.times(1)).getMessagesWithPagination(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
    }

    @Test
    void getMessagesWithPagination_responseCheckTest() {
        List<MessagePayload> payloadList = Arrays.asList(
                new MessagePayload("Admin","2019-01-02 02:44:54-0200"),
                new MessagePayload("Amma","2019-01-02 02:44:54-0200"),
                new MessagePayload("malayalam","2019-01-02 02:44:54-0200"),
                new MessagePayload("123321","2019-01-02 02:44:54-0200"));
        Mockito.when(messageRepository.getMessagesWithPagination(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(payloadList);
        List<EnrichedMessageDTO> receivedResult = service.getMessagesWithPagination(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
        Assertions.assertThat(receivedResult).isNotEmpty();
        Assertions.assertThat(receivedResult).first().hasFieldOrPropertyWithValue("longestPalindromeSize",1L);
        Assertions.assertThat(receivedResult).element(1).hasFieldOrPropertyWithValue("longestPalindromeSize",4L);
        Assertions.assertThat(receivedResult).last().hasFieldOrPropertyWithValue("longestPalindromeSize",0L);
    }
}