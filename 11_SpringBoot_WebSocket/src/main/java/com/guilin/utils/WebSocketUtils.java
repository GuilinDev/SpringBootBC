package com.guilin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketUtils 工具类，用来存储聊天室在线的用户信息，以及发送消息的功能
 */
public final class WebSocketUtils {

    private static Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    //ONLINE_USER_SESSIONS用来存储在线用户，使用 ConcurrentHashMap 提升高并发时效率
    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    // 封装消息发送方法，在发送之前首先判单用户是否存在再进行发送
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message);
        } catch (IOException e) {
            logger.error("sendMessage IOException ", e);
        }
    }

    // 聊天室的消息是所有在线用户可见，因此每次消息的触发实际上是遍历所有在线用户，给每个在线用户发送消息
    public static void sendMessageAll(String message) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session, message));
    }
}
