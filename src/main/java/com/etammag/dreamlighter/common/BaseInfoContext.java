package com.etammag.dreamlighter.common;

import com.etammag.dreamlighter.entity.current.BaseInfo;

public class BaseInfoContext {
    private static final ThreadLocal<BaseInfo> threadLocal = new ThreadLocal<>();

    public static void set(BaseInfo baseInfo){
        threadLocal.set(baseInfo);
    }

    public static BaseInfo get(){
        return threadLocal.get();
    }
}
