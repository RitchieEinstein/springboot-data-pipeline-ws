package com.ritchieeinstein.falcon.dbpersister.domain.service;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.dbpersister.domain.repository.MessagePayloadRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessagePayloadServiceImplTest {

    @Mock
    private MessagePayloadRepository repository;

    private MessagePayloadServiceImpl service;

    @BeforeEach
    void setup(){
        service = new MessagePayloadServiceImpl(repository);
    }

    @Test
    void save_happyCondition() {
        MessagePayload payload = new MessagePayload("TestContent","2019-01-03 05:14+0530");
        Mockito.when(service.save(payload)).thenReturn(payload);
        MessagePayload receivedPayload = service.save(payload);
        Assertions.assertThat(payload).isEqualTo(receivedPayload);
        Mockito.verify(repository,Mockito.times(1)).save(payload);
    }
}