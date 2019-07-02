package com.neo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class) // 代表运行一个 Spring 容器
@SpringBootTest
public class MessageControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before //提前加载 MVC 环境
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //将 saveMessages() 方法添加到 setup() 中，这样启动测试的时候内存中就已经保存了一些数据
        saveMessages();
    }

    @Test
    public void saveMessage() throws Exception {
        // MultiValueMap 用来存储需要发送的请求参数
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); //
        params.add("text", "text");
        params.add("summary", "summary");
        // MockMvcRequestBuilders.post 代表使用 post 请求。
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/message")
        .params(params)).andReturn().getResponse().getContentAsString();

        System.out.println("Result === " + mvcResult);
    }

    // 批量增加消息体
    private void saveMessages() {
        for (int i = 1; i < 10; i++) {
            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", "text" + i);
            params.add("summary", "summary" + i);
            try {
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/message")
                .params(params)).andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getAllMessages() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    // 测试获取单个消息（get 请求)
    @Test
    public void getMessage() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/message/2"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    //测试修改（put 请求）
    @Test
    public void modifyMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "2");
        params.add("text", "text");
        params.add("summary", "summary");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/message").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    //测试局部修改（patch 请求)
    @Test
    public void patchMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "4");
        params.add("text", "text");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/message/text").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    //测试删除（delete 请求
    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/message/2"))
                .andReturn();
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }
}
