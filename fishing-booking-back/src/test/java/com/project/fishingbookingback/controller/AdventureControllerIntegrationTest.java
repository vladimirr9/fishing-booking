package com.project.fishingbookingback.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class AdventureControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void canGetAll() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/fishing-adventures"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(0))))
                .andReturn();
        assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void stringAsIDThrowsBadRequest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/fishing-adventures/a"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void badDateParametersThrowsRequest() throws Exception {
        LocalDateTime l1 = LocalDateTime.now().minusDays(1);
        LocalDateTime l2 = LocalDateTime.now().minusDays(5);
        MvcResult mvcResult = this.mockMvc.perform(get("/api/fishing-adventures/client/freePeriods").param("from", String.valueOf(l1))
                        .param("to", String.valueOf(l2)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andReturn();
    }

}
