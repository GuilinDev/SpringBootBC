package com.guilin.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import static com.guilin.utils.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.guilin.utils.WebSocketUtils.sendMessageAll;

@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    private static Logger logger = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);


    //用户登录聊天室时，将用户信息添加到 ONLINE_USER_SESSIONS 中，同时通知聊天室中的人。
    @OnOpen // @OnOpen在用户建立连接时触发
    public void openSession(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.put(username, session);
        String message = "Welcome [" + username + "] to ChatRoom!";
        logger.info("User Login: " + message);
        sendMessageAll(message);
    }

    //当聊天室某个用户发送消息时，将此消息同步给聊天室所有人
    @OnMessage // @OnMessage监听发送消息的事件
    public void onMessage(@PathParam("username") String username, String message) {
        logger.info(message);
        sendMessageAll("User[" + username + "] : " + message);
    }

    //当用户离开聊天室后，需要将用户信息从 ONLINE_USER_SESSIONS 移除，并且通知到在线的其他用户
    @OnClose //@OnClose 监听用户断开连接事件
    public void onClose(@PathParam("username") String username, Session session) {
        // remove current session
        ONLINE_USER_SESSIONS.remove(username);

        // notify others that current username is removed
        sendMessageAll("User[" + username + "] left ChatRoom");

        try {
            session.close();
        } catch (IOException e) {
            logger.error("onClose error", e);
        }
    }

    // 当 WebSocket 连接出现异常时，出触发 @OnError 事件，可以在此方法内记录下错误的异常信息，并关闭用户连接。
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onError exception", e);
        }
        logger.info("Thorwable msg " + throwable.getMessage());
    }
}
