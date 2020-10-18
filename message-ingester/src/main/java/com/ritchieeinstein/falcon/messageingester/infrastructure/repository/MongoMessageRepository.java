package com.ritchieeinstein.falcon.messageingester.infrastructure.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import javax.validation.Payload;
import java.util.List;
import java.util.UUID;

@EnableMongoRepositories
@Repository
public class MongoMessageRepository implements MessageRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MessagePayload> getAllPayloads() {
        return mongoTemplate.findAll(MessagePayload.class, "messagePayload");
    }
}
