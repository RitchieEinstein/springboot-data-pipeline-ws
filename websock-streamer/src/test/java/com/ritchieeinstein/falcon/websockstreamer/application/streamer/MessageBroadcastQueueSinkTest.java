package com.ritchieeinstein.falcon.websockstreamer.application.streamer;

import com.google.gson.JsonSyntaxException;
import com.ritchieeinstein.falcon.websockstreamer.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.websockstreamer.domain.service.WebSocketMessageService;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageBroadcastQueueSinkTest {

    @Mock
    private WebSocketMessageService webSocketMessageService;

    private MessageBroadcastQueueSink  messageBroadcastQueueSink;

    @BeforeEach
    void setUp(){

        messageBroadcastQueueSink = new MessageBroadcastQueueSink(webSocketMessageService);
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
        Mockito.verify(webSocketMessageService, Mockito.times(1)).pushSocketFrame(ArgumentMatchers.anyObject());
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