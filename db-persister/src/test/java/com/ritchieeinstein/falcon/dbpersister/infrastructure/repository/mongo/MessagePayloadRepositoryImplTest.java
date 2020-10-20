package com.ritchieeinstein.falcon.dbpersister.infrastructure.repository.mongo;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessagePayloadRepositoryImplTest {

    @Mock
    private MongoMessagePayloadRepository mongoMessagePayloadRepository;

    private MessagePayloadRepositoryImpl repo;

    @BeforeEach
    void setup(){
        repo = new MessagePayloadRepositoryImpl(mongoMessagePayloadRepository);
    }

    @Test
    void save() {
        MessagePayload payload = new MessagePayload("TestMessage","2019-04-11 23:44:55+01000");
        Mockito.when(mongoMessagePayloadRepository.save(payload)).thenReturn(payload);
        MessagePayload receivedPayload = repo.save(payload);
        Mockito.verify(mongoMessagePayloadRepository,Mockito.times(1)).save(payload);
        Assertions.assertThat(receivedPayload.getId()).isEqualTo(payload.getId());
        Assertions.assertThat(receivedPayload.getContent()).isEqualTo(payload.getContent());
        Assertions.assertThat(receivedPayload.getTimestamp()).isEqualTo(payload.getTimestamp());
    }
}