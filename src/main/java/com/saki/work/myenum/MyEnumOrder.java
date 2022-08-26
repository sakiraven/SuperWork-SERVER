package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

public enum MyEnumOrder {
    /**
     * 喜欢数量倒序
     */
    LIKE_DESC("like_desc"),

    /**
     * 创建时间倒序
     */
    CREATE_TIME_DESC("create_time_desc"),
    ;

    public final String type;

    MyEnumOrder(String type) {
        this.type = type;
    }
}
