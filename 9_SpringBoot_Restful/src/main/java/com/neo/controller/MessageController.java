package com.neo.controller;

import com.neo.model.Message;
import com.neo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // 获取所有消息体
    @GetMapping(value = "messages")
    public List<Message> list() {
        List<Message> messages = this.messageRepository.findAll();
        return messages;
    }

    // 创建一个消息体
    @PostMapping(value = "message")
    public Message create(Message message) {
        message = this.messageRepository.save(message);
        return message;
    }

    // 使用 put 请求进行修改
    @PutMapping(value = "message")
    public Message modify(Message message) {
        Message message1Result = this.messageRepository.update(message);
        return message1Result;
    }

    // 更新消息的 text 字段
    @PatchMapping(value = "/message/text")
    public Message patch(Message message) {
        Message message1Reuslt = this.messageRepository.updateText(message);
        return message1Reuslt;
    }

    @GetMapping(value = "message/{id}")
    public Message get(@PathVariable Long id) {
        Message message = this.messageRepository.findMessage(id);
        return message;
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }
}
