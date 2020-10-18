package com.ritchieeinstein.falcon.messageingester.domain.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;

import java.util.List;

public interface MessageRepository {

    List<MessagePayload> getAllPayloads();
}
