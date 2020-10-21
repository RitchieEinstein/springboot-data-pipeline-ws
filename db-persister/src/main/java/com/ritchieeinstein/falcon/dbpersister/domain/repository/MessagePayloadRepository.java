package com.ritchieeinstein.falcon.dbpersister.domain.repository;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;

public interface MessagePayloadRepository {

    public MessagePayload save(MessagePayload payload);
}
