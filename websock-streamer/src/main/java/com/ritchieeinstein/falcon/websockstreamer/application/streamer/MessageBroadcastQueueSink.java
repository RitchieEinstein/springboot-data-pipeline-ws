package com.ritchieeinstein.falcon.websockstreamer.application.streamer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class MessageBroadcastQueueSink {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBroadcastQueueSink.class);

    @StreamListener(target = Sink.INPUT)
    public void processIncomingPayload(String payload){
        LOGGER.debug(payload);
    }

}
