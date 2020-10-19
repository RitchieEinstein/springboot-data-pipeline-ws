package com.ritchieeinstein.falcon.messageingester;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

//@DataMongoTest
@Profile("integrationTest")
@ExtendWith(SpringExtension.class)
public class MongoDBSpringIntegrationTest {

    @Test
    public void test(@Autowired MongoTemplate mongoTemplate){
        MessagePayload payload = new MessagePayload("asdfg","tester");
        mongoTemplate.save(payload,"message-payload");

        assertThat(mongoTemplate.findAll(MessagePayload.class,"message-payload").size()).isEqualTo(1);
    }
}
