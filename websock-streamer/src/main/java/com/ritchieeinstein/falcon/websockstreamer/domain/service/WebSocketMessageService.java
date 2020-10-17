package com.ritchieeinstein.falcon.websockstreamer.domain.service;

import javax.validation.Payload;

public interface WebSocketMessageService {

    void pushSocketFrame(Payload payload);
}
