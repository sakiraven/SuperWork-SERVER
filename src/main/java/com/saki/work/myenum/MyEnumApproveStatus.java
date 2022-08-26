package com.saki.work.myenum;

import com.saki.work.utils.LogUtil;

public enum MyEnumApproveStatus {
    NOT_START("NOT_START", "未开始"),
    APPROVED("APPROVED", "通过"),
    REJECTED("REJECTED", "被拒绝"),
    ;

    public final String key;
    public final String content;

    MyEnumApproveStatus(String key, String content) {
        this.key = key;
        this.content = content;
    }
}
