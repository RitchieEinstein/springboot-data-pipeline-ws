package com.ritchieeinstein.falcon.dbpersister.domain.service;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;

public interface PayloadService {

    MessagePayload save(MessagePayload payload);
}
