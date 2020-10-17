package com.ritchieeinstein.falcon.websockstreamer.application.streamer;

import com.google.gson.Gson;
import com.ritchieeinstein.falcon.websockstreamer.application.request.PayloadRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
@Validated
@EnableBinding(Sink.class)
public class MessageBroadcastQueueSink {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBroadcastQueueSink.class);

    @StreamListener(target = Sink.INPUT)
    public void processIncomingPayload(String payload){
        LOGGER.debug(payload);
        PayloadRequest req = new Gson().fromJson(payload, PayloadRequest.class);
        LOGGER.debug(req.getContent() + " : " + req.getTimestamp());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set constraints = factory.usingContext().getValidator().validate(req);
        for(Object violation: constraints){
            LOGGER.error(violation.toString());
        }
    }

}
