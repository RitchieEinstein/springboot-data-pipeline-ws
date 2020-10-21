package com.ritchieeinstein.falcon.websockstreamer.infrastructure.config;

import com.ritchieeinstein.falcon.websockstreamer.domain.service.WebSocketMessagingServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * The WebSocket Has been configured to not operate as a stand alone host for the clients but as a distributed host service.
 * In order to maintain the state of the broadcast being sent to all the users, the messages will be pushed to a relay host
 * (in our case RabbitMQ) which has STOMP Support and all the messages will be read from there.
 *
 * This configuration part is designed to achieve it.
 */
@Configuration
@EnableWebSocketMessageBroker
public class RelayWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${spring.rabbitmq.host}")
    private String hostname;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic").setRelayHost(hostname)
                .setRelayPort(61613)
                .setClientLogin("guest").setClientPasscode("guest");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/conn").setAllowedOrigins("*");
        registry.addEndpoint("/conn").withSockJS();
    }
}
