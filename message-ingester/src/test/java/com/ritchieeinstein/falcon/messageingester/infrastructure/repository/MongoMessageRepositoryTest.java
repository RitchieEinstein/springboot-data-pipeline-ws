package com.ritchieeinstein.falcon.messageingester.infrastructure.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class MongoMessageRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    private MongoMessageRepository repo;

    @BeforeEach
    void setUp(){
        repo = new MongoMessageRepository(mongoTemplate);
    }

    @Test
    void getAllPayloads_withNoRecords(){
        Mockito.when(mongoTemplate.findAll(MessagePayload.class,"messagePayload")).thenReturn(Collections.emptyList());
        List<MessagePayload> messagePayloads = repo.getAllPayloads();
        Assertions.assertThat(messagePayloads.isEmpty()).isEqualTo(true);
        Mockito.verify(mongoTemplate,Mockito.times(1)).findAll(ArgumentMatchers.eq(MessagePayload.class),ArgumentMatchers.anyString());
    }

    @Test
    void getAllPayloads_withSomeRecords(){
        List<MessagePayload> expectedPayloadList = Arrays.asList(
                new MessagePayload("Amma","2020-10-11 04:04:05+01:00"),
                new MessagePayload("Amanda","2020-10-11 04:04:05+01:00"),
                new MessagePayload("Russia","2020-10-11 04:04:05+01:00"),
                new MessagePayload("Malayalam","2020-10-11 04:04:05+01:00"),
                new MessagePayload("ritchie.einstein@gmail.com","2020-10-11 04:04:05+01:00")
        );
        Mockito.when(mongoTemplate.findAll(MessagePayload.class,"messagePayload")).thenReturn(expectedPayloadList);
        List<MessagePayload> receivedPayloadList = repo.getAllPayloads();
        Assertions.assertThat(receivedPayloadList.size()).isEqualTo(expectedPayloadList.size());
        Assertions.assertThat(receivedPayloadList).isEqualTo(expectedPayloadList);
    }

    @Test
    void getAllPayloads_getCorrectClassType(){
        List<MessagePayload> messagePayloads = repo.getAllPayloads();
        Mockito.verify(mongoTemplate,Mockito.times(1)).findAll(MessagePayload.class,"messagePayload");
    }

    @Test
    void getMessagesWithPagination_queryWithPageAnd(){
        repo.getMessagesWithPagination(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
        Mockito.verify(mongoTemplate,Mockito.times(1)).find(ArgumentMatchers.any(Query.class),ArgumentMatchers.eq(MessagePayload.class));
    }

}
