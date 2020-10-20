package com.ritchieeinstein.falcon.messageingester.infrastructure.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
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
        template.save(new MessagePayload("12345","tester"),"messagePayload");
        List<MessagePayload> list = repo.getAllPayloads();
        assertThat(list.size()).isEqualTo(1);

    }

    @Test
    void getMessagesWithPagination() {
        template.save(new MessagePayload("12345","tester"),"messagePayload");
        List<MessagePayload> list = repo.getMessagesWithPagination(0,15);
        assertThat(list.size()).isEqualTo(1);
    }
}
