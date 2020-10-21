package com.ritchieeinstein.falcon.messageingester.domain.service;

import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.repository.MessageRepository;
import com.ritchieeinstein.falcon.messageingester.infrastructure.repository.MongoMessageRespositoryTestHelper;
import com.ritchieeinstein.falcon.messageingester.infrastructure.streamer.CloudStreamerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
public class MessagePayloadServiceImplIntegrationTest {

    @Autowired
    private MessagePayloadService messagePayloadService;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private CloudStreamerService cloudStreamerService;

    @TestConfiguration
    static class MessagePayloadServiceImplTestContextConfiguration{

        @Bean
        public MessagePayloadService messagePayloadService(){
            return new MessagePayloadServiceImpl();
        }
    }

    @BeforeEach
    public void setUp(){
        List<MessagePayload> messagePayloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        List<EnrichedMessageDTO> dtoList = new LinkedList<>();
        dtoList = messagePayloadList.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
//        Mockito.when(messageRepository.getAllPayloads()).thenReturn(messagePayloadList);
    }



    @Test
    void getAllMessages() {
        List<MessagePayload> messagePayloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        List<EnrichedMessageDTO>  dtoList = messagePayloadList.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
        List<EnrichedMessageDTO> incomingMessagePayloadList = messagePayloadService.getAllMessages();
        Assertions.assertThat(dtoList.containsAll(incomingMessagePayloadList)).isEqualTo(true);
    }


}
