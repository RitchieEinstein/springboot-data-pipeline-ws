package com.ritchieeinstein.falcon.dbpersister.application.streamer;

import com.google.gson.JsonSyntaxException;
import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.dbpersister.domain.service.MessagePayloadServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageBroadcastQueueSinkTest {

    @Mock
    private MessagePayloadServiceImpl messagePayloadService;

    private MessageBroadcastQueueSink  messageBroadcastQueueSink;

    @BeforeEach
    void setUp(){

        messageBroadcastQueueSink = new MessageBroadcastQueueSink(messagePayloadService);
    }

    @Test
    void processIncomingPayload_nullPassed() throws Exception {
        Assertions.assertThatThrownBy(()->{
            messageBroadcastQueueSink.processIncomingPayload(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void processIncomingPayload_randomStringPassed() throws Exception {
        Assertions.assertThatThrownBy(()->{
            messageBroadcastQueueSink.processIncomingPayload("qwerty");
        }).isInstanceOf(JsonSyntaxException.class);
    }

    @Test
    void processIncomingPayload_differentJsonSent() throws Exception {
        Assertions.assertThatThrownBy(()->{
            messageBroadcastQueueSink.processIncomingPayload("{\"name\":\"foo\",\"age\":22");
        }).isInstanceOf(JsonSyntaxException.class);
    }

    @Test
    void processIncomingPayload_properJSON() throws Exception {
        messageBroadcastQueueSink.processIncomingPayload("{\"content\":\"amma\", \"timestamp\":\"2019-10-11 23:23:24+0530\"}");
        Mockito.verify(messagePayloadService, Mockito.times(1)).save(ArgumentMatchers.any(MessagePayload.class));
    }

    @Test
    void processIncomingPayload_jsonWithEmptyContent() throws Exception {
        Assertions.assertThatThrownBy(()->{
            messageBroadcastQueueSink.processIncomingPayload("{\"content\":\"\", \"timestamp\":\"2019-10-11 23:23:24+0530\"}");
        }).isInstanceOf(Exception.class).hasMessage("Validation Failed");
    }

    @Test
    void processIncomingPayload_jsonWithInvalidTimeStamp() throws Exception {
        Assertions.assertThatThrownBy(()->{
            messageBroadcastQueueSink.processIncomingPayload("{\"content\":\"\", \"timestamp\":\"2019-13-33 23:23:24+0530\"}");
        }).isInstanceOf(Exception.class).hasMessage("Validation Failed");
    }
}