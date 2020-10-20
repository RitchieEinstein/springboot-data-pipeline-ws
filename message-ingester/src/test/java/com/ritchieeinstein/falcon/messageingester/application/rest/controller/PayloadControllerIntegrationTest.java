package com.ritchieeinstein.falcon.messageingester.application.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritchieeinstein.falcon.messageingester.application.response.EnrichedMessageDTO;
import com.ritchieeinstein.falcon.messageingester.domain.model.MessagePayload;
import com.ritchieeinstein.falcon.messageingester.domain.service.MessagePayloadService;
import com.ritchieeinstein.falcon.messageingester.infrastructure.repository.MongoMessageRespositoryTestHelper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PayloadController.class)
public class PayloadControllerIntegrationTest {

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

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Test
    void ingestMessagePayload() throws Exception {
        mvc.perform(post("/message")
        .contentType(APPLICATION_JSON_UTF8)
        .content(asJsonString(new MessagePayload("kannada","2019-12-11 04:54:22+0530")))
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void ingestMessagePayload_withInvalidContent() throws Exception {
        mvc.perform(post("/message")
                .contentType(APPLICATION_JSON_UTF8)
                .content(asJsonString(new MessagePayload(null,"2019-12-11 04:54:22+0530")))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ingestMessagePayload_withInvalidTimestamp() throws Exception {
        mvc.perform(post("/message")
                .contentType(APPLICATION_JSON_UTF8)
                .content(asJsonString(new MessagePayload("INDIAN","2019-12-11 44:54:22+0530")))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllEnrichedMessages() throws Exception {
        List<MessagePayload> payloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        List<EnrichedMessageDTO> enrichedMessageDTOS = payloadList.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
        given(messagePayloadService.getAllMessages()).willReturn(enrichedMessageDTOS);

        mvc.perform(get("/message/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(payloadList.size())))
                .andExpect(jsonPath("$[0].content",is("Message1")));
    }

    @Test
    void getMessagesWithPagination_baseCondition() throws Exception {
        List<MessagePayload> payloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        List<EnrichedMessageDTO> enrichedMessageDTOS = payloadList.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
        given(messagePayloadService.getAllMessages()).willReturn(enrichedMessageDTOS.subList(0,5));
        mvc.perform(get("/message")
                .queryParam("page","0").queryParam("size","5"))
                .andExpect(status().isOk());
    }

    @Test
    void getMessagesWithPagination_invalidPage() throws Exception {
        List<MessagePayload> payloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        List<EnrichedMessageDTO> enrichedMessageDTOS = payloadList.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
        given(messagePayloadService.getAllMessages()).willReturn(enrichedMessageDTOS.subList(0,5));
        mvc.perform(get("/message")
                .queryParam("page","-1").queryParam("size","5"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMessagesWithPagination_invalidSize() throws Exception {
        List<MessagePayload> payloadList = MongoMessageRespositoryTestHelper.getAllMongoMessagePayloadMessages();
        List<EnrichedMessageDTO> enrichedMessageDTOS = payloadList.stream().map(EnrichedMessageDTO::new).collect(Collectors.toList());
        given(messagePayloadService.getAllMessages()).willReturn(enrichedMessageDTOS.subList(0,5));
        mvc.perform(get("/message")
                .queryParam("page","2").queryParam("size","50"))
                .andExpect(status().isBadRequest());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
