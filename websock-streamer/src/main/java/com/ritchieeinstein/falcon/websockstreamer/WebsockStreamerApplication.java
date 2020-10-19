package com.ritchieeinstein.falcon.websockstreamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WebsockStreamerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsockStreamerApplication.class, args);
	}

}
