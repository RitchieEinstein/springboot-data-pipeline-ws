package com.ritchieeinstein.falcon.messageingester.infrastructure.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoMessageRepositoryIntegrationTest {

    @Autowired
    private MongoTemplate template;

    private MongoMessageRepository repo;

    @BeforeEach
    void setUp() {
        repo = new MongoMessageRepository(template);
    }
    @AfterEach
    public void tearDown() throws Exception {
        template.dropCollection("messagePayload");
    }

    @Test
    void getAllPayloads() {
        List<MessagePayload> defaultPayloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        for(MessagePayload payload : defaultPayloadList) template.save(payload, "messagePayload");
//        template.save(new MessagePayload("12345","tester"),"messagePayload");
        List<MessagePayload> list = repo.getAllPayloads();
        for(MessagePayload payload : defaultPayloadList) assertThat(list);

    }

    @Test
    void getMessagesWithPagination() {
        List<MessagePayload> defaultPayloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        for(MessagePayload payload : defaultPayloadList) template.save(payload, "messagePayload");
        List<MessagePayload> list = repo.getMessagesWithPagination(0,5);
        System.out.println(list);
        assertThat(list.size()).isEqualTo(5);
        assertThat(list).containsSequence(defaultPayloadList.get(0),defaultPayloadList.get(16),defaultPayloadList.get(1), defaultPayloadList.get(15), defaultPayloadList.get(2));

    }


}
