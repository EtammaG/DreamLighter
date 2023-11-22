package com.etammag.dreamlighter.service.common;

import com.etammag.icommon.entity.BaseInfo;

public interface SignService {
    String login(String username, String password, int type);

    void logout(BaseInfo baseInfo);
}
