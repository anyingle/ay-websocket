package yingl.an.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yingl.an.domain.enums.MessageType;
import yingl.an.domain.vo.MessageDTO;
import yingl.an.domain.vo.WeChatVO;
import yingl.an.infra.utils.GsonSingleton;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/connect/weChat/{username}")
public class WeChatWebSocket {

    private static final Map<String, WeChatWebSocket> CLIENTS = new ConcurrentHashMap<>();
    private static int onlineCount = 0;
    private Session session;
    private String username;

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WeChatWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WeChatWebSocket.onlineCount--;
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        log.info("{} login", username);
        if (CLIENTS.containsKey(username)) {
            log.info("{} already login", username);
            return;
        }
        this.username = username;
        this.session = session;
        addOnlineCount();
        CLIENTS.put(username, this);
    }

    @OnClose
    public void onClose() {
        log.info("{} logout", username);
        CLIENTS.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) {
        WeChatVO weChat = GsonSingleton.fromJson(message, WeChatVO.class);
        String msg = weChat.getMessage();
        String to = weChat.getTo();
        if (StringUtils.isNotBlank(to)) {
            sendMessageTo(msg, to);
        } else {
            sendMessageAll(new MessageDTO(MessageType.MESSAGE, this.username, msg));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String msg, String to) {
        WeChatWebSocket socket = CLIENTS.get(to);
        if (null != socket) {
            socket.session.getAsyncRemote().sendText(GsonSingleton.toJson(new MessageDTO(MessageType.MESSAGE, this.username, msg)));
        }
    }

    public void sendMessageAll(MessageDTO message) {
        CLIENTS.values().forEach(r -> r.session.getAsyncRemote().sendText(GsonSingleton.toJson(message)));
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleSendMessage() {
        this.sendMessageAll(new MessageDTO(MessageType.ONLINE, "system", String.valueOf(WeChatWebSocket.getOnlineCount())));
    }
}