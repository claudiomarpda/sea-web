package com.sea.web.seaweb.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSignInShouldSucceed() throws Exception {
        this.mockMvc.perform(get("/signin")).andExpect(status().isOk());
    }

    @Test
    public void getSignInErrorShouldSucceed() throws Exception {
        this.mockMvc.perform(get("/signin/error")).andExpect(status().isOk());
    }
}
