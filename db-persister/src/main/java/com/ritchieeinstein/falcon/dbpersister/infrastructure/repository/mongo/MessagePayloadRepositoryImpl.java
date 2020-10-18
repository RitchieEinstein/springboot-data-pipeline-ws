package com.ritchieeinstein.falcon.dbpersister.infrastructure.repository.mongo;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.dbpersister.domain.repository.MessagePayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessagePayloadRepositoryImpl implements MessagePayloadRepository {

    @Autowired
    private MongoMessagePayloadRepository repo;

    @Override
    public MessagePayload save(MessagePayload payload) {
        return repo.save(payload);
    }
}
