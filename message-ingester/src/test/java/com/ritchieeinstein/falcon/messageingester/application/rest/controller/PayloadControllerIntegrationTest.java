package com.ritchieeinstein.falcon.messageingester.application.rest.controller;

import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.service.MessagePayloadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PayloadController.class)
@Profile("integrationTest")
class PayloadControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessagePayloadService messagePayloadService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllEnrichedMessages() throws Exception {
        List<EnrichedMessageDTO> enrichedMessageDTOS = Arrays.asList(new EnrichedMessageDTO(new MessagePayload("amma","Tester")));
        given(messagePayloadService.getAllMessages()).willReturn(enrichedMessageDTOS);

        mvc.perform(get("/message/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content",is("amma")));
    }

    @Test
    void getMessagesWithPagination() {
    }
}