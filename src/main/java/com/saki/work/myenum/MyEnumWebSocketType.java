package com.saki.work.myenum;


import com.saki.work.utils.LogUtil;

public enum MyEnumWebSocketType {
    HEART("heart", "心跳"),
    CONNECTION("connection", "链接数据"),
    TEST("test", "测试数据"),
    USER_INFO_NUM("user_info_num", "用户未读消息"),
    ;

    public final String type;
    public final String content;

    MyEnumWebSocketType(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static MyEnumWebSocketType getEnumByType(String type) {
        for (MyEnumWebSocketType item : values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return null;
    }
}
