package com.ritchieeinstein.falcon.messageingester.application.rest.controller;

import com.ritchieeinstein.falcon.messageingester.application.request.PayloadRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class PayloadController {

    private Logger LOGGER = LoggerFactory.getLogger(PayloadController.class);

    @PostMapping("/message")
    public ResponseEntity<PayloadRequest> ingestMessagePayload(@Valid @RequestBody PayloadRequest payloadRequest){
        LOGGER.debug(payloadRequest.getContent() + " " + payloadRequest.getTimestamp());
        return new ResponseEntity<>(payloadRequest, HttpStatus.ACCEPTED);
    }


}