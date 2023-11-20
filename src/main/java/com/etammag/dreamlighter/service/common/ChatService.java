package com.etammag.dreamlighter.service.common;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ChatService {

    SseEmitter receive(Long fromId);

    void send(Long toId, String msg);

}
