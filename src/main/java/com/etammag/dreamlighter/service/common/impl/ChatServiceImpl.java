package com.etammag.dreamlighter.service.common.impl;

import com.etammag.dreamlighter.common.exception.CustomException;
import com.etammag.dreamlighter.entity.common.db.Chat;
import com.etammag.dreamlighter.entity.current.BaseInfo;
import com.etammag.dreamlighter.mapper.common.mp.ChatMapper;
import com.etammag.dreamlighter.service.common.ChatService;
import com.etammag.dreamlighter.common.BaseInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    private final HashMap<Re, SseEmitter> session;

    @Autowired
    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
        this.session = new HashMap<>();
    }

    @Override
    public SseEmitter receive(Long fromId) {
        return receive(fromId, BaseInfoContext.get());
    }

    public SseEmitter receive(Long fromId, BaseInfo to) {
        if (to.getType() != 2 && to.getType() != 3) return null;
        SseEmitter sseEmitter = new SseEmitter(-1L);
        try {
            List<String> msgs = chatMapper.selectAllMsgByBoth(
                    to.getId(),
                    fromId,
                    to.getType() == 2 ? 0 : 1);
            for(String msg : msgs)
                sseEmitter.send(msg);

        } catch (IOException e) {
            log.warn("连接失败", e);
            throw new CustomException("连接失败");
        }
        Re re = new Re(to.getId(), to.getType() == 2 ? 0 : 1);
        sseEmitter.onCompletion(() -> session.remove(re));
        sseEmitter.onTimeout(() -> session.remove(re));
        sseEmitter.onError((throwable) -> session.remove(re));
        session.put(re, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void send(Long toId, String msg) {
        send(BaseInfoContext.get(), toId, msg);
    }

    public void send(BaseInfo from, Long toId, String msg) {
        Re re = new Re(toId, from.getType() == 2 ? 1 : 0);
        SseEmitter sseEmitter = session.get(re);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(msg);
            } catch (IOException e) {
                log.error("发送失败", e);
                throw new CustomException("发送失败");
            }
        } else {
            chatMapper.insert(new Chat(
                    msg,
                    from.getId(),
                    toId,
                    from.getType() == 2 ? 1 : 0, LocalDateTime.now()));
        }
    }

    private class Re {
        private long id;
        private int type;
        public Re(long id, int type) {
            this.id = id;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Re)) return false;
            Re re = (Re) o;
            return id == re.id && type == re.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, type);
        }
    }

}
