package com.ritchieeinstein.falcon.websockstreamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The WebSocket Streamer Microservice to ingest the messages coming from the Message Ingester, and broadcast the
 * messages to all subscribers through websockets.
 *
 * NOTE: In order to execute the app in local, set the env as local in the application.properties
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class WebsockStreamerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsockStreamerApplication.class, args);
	}

}
