package com.ritchieeinstein.falcon.messageingester.application.rest.controller;

import com.ritchieeinstein.falcon.messageingester.application.request.PayloadRequest;
import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.application.response.GenericResponse;
import com.ritchieeinstein.falcon.messageingester.domain.service.MessagePayloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class PayloadController {

    private Logger LOGGER = LoggerFactory.getLogger(PayloadController.class);

    @Autowired
    private MessagePayloadService messagePayloadService;

    @PostMapping("/message")
    public ResponseEntity<GenericResponse<PayloadRequest>> ingestMessagePayload(@Valid @RequestBody PayloadRequest payloadRequest){
        LOGGER.debug(payloadRequest.getContent() + " " + payloadRequest.getTimestamp());
        messagePayloadService.pushMessageIntoQueue(payloadRequest.getMessagePayload());
        return new ResponseEntity<>(new GenericResponse<>(payloadRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/message/all")
    public List<EnrichedMessageDTO> getAllEnrichedMessages(){
        return messagePayloadService.getAllMessages();
    }


}
