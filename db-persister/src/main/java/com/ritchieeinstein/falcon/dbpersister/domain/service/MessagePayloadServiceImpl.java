package com.ritchieeinstein.falcon.dbpersister.domain.service;

import com.ritchieeinstein.falcon.dbpersister.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.dbpersister.domain.repository.MessagePayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePayloadServiceImpl implements PayloadService{

    private MessagePayloadRepository repo;

    public MessagePayloadServiceImpl(MessagePayloadRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public void setRepo(MessagePayloadRepository repo) {
        this.repo = repo;
    }

    @Override
    public MessagePayload save(MessagePayload payload) {
        return repo.save(payload);
    }
}
