package com.ritchieeinstein.falcon.messageingester.infrastructure.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableMongoRepositories
@Repository
public class MongoMessageRepository implements MessageRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoMessageRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MessagePayload> getAllPayloads() {
        return mongoTemplate.findAll(MessagePayload.class, "messagePayload");
    }

    @Override
    public List<MessagePayload>  getMessagesWithPagination(int pageNum, int size){
        Query query = new Query().with(Sort.by(Sort.Direction.ASC,"timestamp")).skip(pageNum * size).limit(size);
        return mongoTemplate.find(query, MessagePayload.class);
    }
}
