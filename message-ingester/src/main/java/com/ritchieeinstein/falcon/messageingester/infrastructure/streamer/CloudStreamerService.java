package com.ritchieeinstein.falcon.messageingester.infrastructure.streamer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * The CloudStreamer utilizes the Spring Cloud Streamer for communications with the Messaging system.
 *
 * They can be
 * Output -> Pushes Message into a Channel,
 * Sink -> Pulls Message from a Channel
 * Processor -> Pulls Message from a Channel and Process it and finally push into another channel.
 *
 * For our requirement, we've used the @Output.
 *
 */
public interface CloudStreamerService {

    @Output("IncomingMessageChannel")
    MessageChannel pushPayload();
}
