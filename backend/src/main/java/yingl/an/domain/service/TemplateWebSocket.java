package yingl.an.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@ServerEndpoint(value = "/connect/random")
public class TemplateWebSocket {

    private static final List<Session> CLIENT = new CopyOnWriteArrayList<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        log.info("on open {}", session);
        CLIENT.add(session);
        this.session = session;
    }

    @OnClose
    public void onClose() {
        log.info("on close");
        CLIENT.remove(this.session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("on message {}", message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("on error {}", error.getMessage());
    }

    public void sendMessageAll(String message) {
        CLIENT.forEach(r -> r.getAsyncRemote().sendText(message));
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleSendMessage() {
        this.sendMessageAll("" + Math.random());
    }
}