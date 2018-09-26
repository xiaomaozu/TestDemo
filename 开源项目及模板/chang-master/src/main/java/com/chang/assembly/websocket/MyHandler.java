package com.chang.assembly.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author 常培兵
 * @Description: WebSocket处理程序类（即WebSocket服务器）
 * @date 2018/4/18 14:32
 */
public class MyHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String clientMessage = message.getPayload();

        if (clientMessage.startsWith("Hello") || clientMessage.startsWith("Hi")) {
            session.sendMessage(new TextMessage("Hello! What can i do for you?"));
        } else {
            int i=0;
            while (true) {
                session.sendMessage(new TextMessage(i+"服务器实时打印；"));
                Thread.sleep(5000);
                i++;
            }
        }
    }
}
