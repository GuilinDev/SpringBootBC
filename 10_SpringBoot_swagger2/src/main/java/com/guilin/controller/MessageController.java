package com.guilin.controller;


import com.guilin.config.BaseResult;
import com.guilin.model.Message;
import com.guilin.repository.MessageRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Api 作用在 Controller 类上，做为 Swagger 文档资源，该注解将一个 Controller（Class）标注为一个 Swagger 资源（API）。
@Api(value = "消息", description = "消息操作 API", position = 100, protocols = "http")
@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @ApiOperation( //ApiOperation 定义在方法上，描述方法名、方法解释、返回信息、标记等信息。
            value = "消息列表",
            notes = "完整的消息内容列表",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            response = List.class)
    @GetMapping(value = "messages")
    public List<Message> list() {
        List<Message> messages = this.messageRepository.findAll();
        return messages;
    }

    @ApiOperation(
            value = "添加消息",
            notes = "根据参数创建消息"
    )
    @ApiImplicitParams({
            // @ApiImplicitParams 用于描述方法的返回信息，和 @ApiImplicitParam 注解配合使用；
            // @ApiImplicitParam 用来描述具体某一个参数的信息，包括参数的名称、类型、限制等信息
            @ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "message")
    public Message create(Message message) {
        System.out.println("message====" + message.toString());
        message = this.messageRepository.save(message);
        return message;
    }

    @ApiOperation(
            value = "修改消息",
            notes = "根据参数修改消息"
    )
    @PutMapping(value = "message")
    @ApiResponses({
            // @ApiResponses 主要封装方法的返回信息和 @ApiResponse 配置起来使用，
            // @ApiResponse 定义返回的具体信息包括返回码、返回信息等。
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁止访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    public Message modify(Message message) {
        Message messageResult = this.messageRepository.update(message);
        return messageResult;
    }

    @PatchMapping(value = "/message/text")
    public BaseResult<Message> patch(Message message) {
        Message messageResult = this.messageRepository.updateText(message);
        return BaseResult.successWithData(messageResult);
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

