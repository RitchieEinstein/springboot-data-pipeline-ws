package com.ritchieeinstein.falcon.dbpersister.infrastructure.repository.mongo;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableMongoRepositories
@Repository
public interface MongoMessagePayloadRepository extends MongoRepository<MessagePayload, UUID> {
}
