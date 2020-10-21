package com.ritchieeinstein.falcon.dbpersister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The DB Persister Microservice to ingest the messages coming from the Message ingester, validate them and push into the DB
 *
 * Note: In order to execute the app in local, set the env as local in the application.properties
 *
 */
@SpringBootApplication
public class DbPersisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbPersisterApplication.class, args);
	}

}
