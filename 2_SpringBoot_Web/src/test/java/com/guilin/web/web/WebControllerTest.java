package com.guilin.web.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class WebControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
    }

    @Test
    public void getUser() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/getUser"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result: " + responseString);
    }

    @Test
    public void getUsers() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/getUsers"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result: " + responseString);
    }

//    @Test
//    public void get() throws Exception {
//        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/get"))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println("result: " + responseString);
//    }
}
