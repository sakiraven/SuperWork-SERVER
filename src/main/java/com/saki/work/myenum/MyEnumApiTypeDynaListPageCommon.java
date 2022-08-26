package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

public enum MyEnumApiTypeDynaListPageCommon {
    DYNA_HOME(0, "代x主页"),
    SELF_SEND(1, "我的发布"),
    SELF_ACCEPT(2, "我的接单"),
    ;

    public final Integer type;
    public final String content;

    MyEnumApiTypeDynaListPageCommon(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public static String getContentByType(Integer type) {
        for (MyEnumApiTypeDynaListPageCommon item : values()) {
            if (item.type.equals(type)) {
                return item.content;
            }
        }
        LogUtil.notFoundMyEnum(type);
        return MyEnumDataDefault.UNDEFINED.content;
    }

    public Integer getType() {
        return type;
    }
}
