package com.etammag.dreamlighter.service.common;

public interface SignService {
    String login(String username, String password, int type);

    void logout();
}
