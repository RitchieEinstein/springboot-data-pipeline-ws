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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class PayloadController {

    private final Logger LOGGER = LoggerFactory.getLogger(PayloadController.class);
    private MessagePayloadService messagePayloadService;

    public PayloadController(MessagePayloadService messagePayloadService){
        this.messagePayloadService = messagePayloadService;
    }

    @Autowired
    public void setMessagePayloadService(MessagePayloadService messagePayloadService) {
        this.messagePayloadService = messagePayloadService;
    }

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

    @GetMapping(value = "/message", params = {"page","size"})
    public List<EnrichedMessageDTO> getMessagesWithPagination(@RequestParam("page") @Min(0) int page, @RequestParam("size") @Min(0) @Max(25) int size){
        return messagePayloadService.getMessagesWithPagination(page, size);
    }
}
