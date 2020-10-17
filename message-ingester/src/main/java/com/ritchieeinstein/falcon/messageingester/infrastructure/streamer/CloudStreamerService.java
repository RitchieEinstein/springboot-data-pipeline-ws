package com.ritchieeinstein.falcon.messageingester.infrastructure.streamer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CloudStreamerService {

    @Output("IncomingMessageChannel")
    MessageChannel pushPayload();
}
