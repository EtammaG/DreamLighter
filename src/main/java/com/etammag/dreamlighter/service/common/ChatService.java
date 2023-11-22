package com.etammag.dreamlighter.service.common;

import com.etammag.icommon.entity.BaseInfo;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ChatService {

    SseEmitter receive(Long fromId, BaseInfo to);

    void send(BaseInfo from, Long toId, String msg);

}
