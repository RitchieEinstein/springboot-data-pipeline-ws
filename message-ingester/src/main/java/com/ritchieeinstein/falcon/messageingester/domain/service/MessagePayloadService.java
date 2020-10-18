package com.ritchieeinstein.falcon.messageingester.domain.service;

import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;

import java.util.List;


public interface MessagePayloadService {

    void pushMessageIntoQueue(MessagePayload payload);

    List<EnrichedMessageDTO> getAllMessages();

    List<EnrichedMessageDTO> getMessagesWithPagination(int pageNum, int pageSize);
}
