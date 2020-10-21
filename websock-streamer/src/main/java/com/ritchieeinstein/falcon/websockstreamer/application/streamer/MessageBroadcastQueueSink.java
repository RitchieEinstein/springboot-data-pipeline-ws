package com.ritchieeinstein.falcon.websockstreamer.application.streamer;

import com.google.gson.Gson;
import com.ritchieeinstein.falcon.websockstreamer.application.request.PayloadRequest;
import com.ritchieeinstein.falcon.websockstreamer.domain.service.WebSocketMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * The MessageBroadcastQueueSink utilizes the Spring Cloud Streamer for communications with the Messaging system.
 *
 * They can be
 * Output -> Pushes Message into a Channel,
 * Sink -> Pulls Message from a Channel
 * Processor -> Pulls Message from a Channel and Process it and finally push into another channel.
 *
 * For our requirement, we've used the @Sink to pull message from the queue.
 *
 */

@Component
@Validated
@EnableBinding(Sink.class)
public class MessageBroadcastQueueSink {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBroadcastQueueSink.class);
    private static final Gson gson = new Gson();

    private WebSocketMessageService webSocketMessageService;

    public MessageBroadcastQueueSink(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    @Autowired
    public void setWebSocketMessageService(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    @StreamListener(target = Sink.INPUT)
    public void processIncomingPayload(String payload) throws Exception {
        LOGGER.debug(payload);
        PayloadRequest req = gson.fromJson(payload, PayloadRequest.class);
        Set constraints = Validation.buildDefaultValidatorFactory().usingContext().getValidator().validate(req);
        if((long) constraints.size() > 0){
            for(Object violation: constraints){
                LOGGER.error(violation.toString());
            }
            throw new Exception("Validation Failed");
        }
        webSocketMessageService.pushSocketFrame(req.getMessagePayload());
    }

}
