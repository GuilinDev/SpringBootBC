package com.guilin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdempotenceTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void interfaceIdempotenceTest() throws Exception {
        // initiate Mockmvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // mimic calling token endpoint
        String token = mockMvc.perform(MockMvcRequestBuilders.get("/token")
                .accept(MediaType.TEXT_HTML))
                    .andReturn()
                    .getResponse().getContentAsString();
        log.info("Got Token String: {}", token);

        // iterate 5 times to test
        for (int i = 0; i < 5; i++) {
            log.info("the {}th calling endpoint", i);
            // call endpoint and print result
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/test")
                .header("token", token)
                .accept(MediaType.TEXT_HTML))
                    .andReturn()
                    .getResponse().getContentAsString();
            log.info(result);
            if (i == 0) {
                Assert.assertEquals(result, "Idempotence OK - normal call");
            } else {
                Assert.assertEquals(result, "Idempotence Repeated");
            }
        }
    }
}
