package com.ritchieeinstein.falcon.messageingester.infrastructure.repository;

import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;

import java.util.Arrays;
import java.util.List;

public class MongoMessageRespositoryTestHelper {

    public static List<MessagePayload> getAllMongoMessagePayloadMessages(){
        return Arrays.asList(
                new MessagePayload("Message1", "2011-02-05 12:22:33+0100"),
                new MessagePayload("Message2", "2012-02-05 12:22:33+0100"),
                new MessagePayload("Message3", "2013-02-05 12:22:33+0100"),
                new MessagePayload("Message4", "2014-02-05 12:22:33+0100"),
                new MessagePayload("Message5", "2015-02-05 12:22:33+0100"),
                new MessagePayload("Message6", "2016-02-05 12:22:33+0100"),
                new MessagePayload("Message7", "2017-02-05 12:22:33+0100"),
                new MessagePayload("Message8", "2018-02-05 12:22:33+0100"),
                new MessagePayload("Message9", "2019-02-05 12:22:33+0100"),
                new MessagePayload("Message10", "2018-02-05 12:22:33+0100"),
                new MessagePayload("Message11", "2017-02-05 12:22:33+0100"),
                new MessagePayload("Message12", "2016-02-05 12:22:33+0100"),
                new MessagePayload("Message13", "2015-02-05 12:22:33+0100"),
                new MessagePayload("Message14", "2014-02-05 12:22:33+0100"),
                new MessagePayload("Message15", "2013-02-05 12:22:33+0100"),
                new MessagePayload("Message16", "2012-02-05 12:22:33+0100"),
                new MessagePayload("Message17", "2011-02-05 12:22:33+0100"));
    }
}
