package com.reply.tddSpring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class TddSpringApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LoadBalancerService loadBalancerService;


    @BeforeEach
    void setUp() {
        this.loadBalancerService.getRegistry().clear();
    }

    @Test
    void shouldGetAllLBs() throws Exception {
        this.loadBalancerService.register(URI.create("http://localhost:8080/0"));
        this.loadBalancerService.register(URI.create("http://localhost:8080/1"));


        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/lb/all")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    void shouldGetLB() throws Exception {
        final String expectedLB = "http://localhost:8080/1";

        this.loadBalancerService.register(URI.create(expectedLB));

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/lb")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(expectedLB))
                .andDo(print());
    }
}
