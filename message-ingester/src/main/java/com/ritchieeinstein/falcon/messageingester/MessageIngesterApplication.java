package com.ritchieeinstein.falcon.messageingester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Message Ingester Microservice to ingest the messages coming in as a POST request, validate them and push into the queue
 *
 * Note: In order to execute the app in local, set the env as local in the application.properties
 * 
 */

@SpringBootApplication
@EnableEurekaClient
public class MessageIngesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageIngesterApplication.class, args);
	}

}
