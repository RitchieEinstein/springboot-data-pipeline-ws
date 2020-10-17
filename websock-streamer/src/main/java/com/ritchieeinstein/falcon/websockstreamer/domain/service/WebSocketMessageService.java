package com.ritchieeinstein.falcon.websockstreamer.domain.service;

import com.ritchieeinstein.falcon.websockstreamer.domain.model.MessagePayload;

public interface WebSocketMessageService {

    void pushSocketFrame(MessagePayload payload);
}
